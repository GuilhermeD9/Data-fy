package dev.gui.data_fy.service;

import dev.gui.data_fy.client.AlbumSpotifyClient;
import dev.gui.data_fy.client.AuthSpotifyClient;
import dev.gui.data_fy.model.Album;
import dev.gui.data_fy.model.LoginRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyService {
    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public SpotifyService(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    public List<Album> getNewReleases(String clientId, String clientSecret) {
        var request = new LoginRequest("client_credentials", clientId, clientSecret);
        var token = authSpotifyClient.login(request).getAccessToken();
        var response = albumSpotifyClient.getReleases("Bearer " + token);
        return response.getAlbums().getItems();
    }
}
