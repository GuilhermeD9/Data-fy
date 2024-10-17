package dev.gui.data_fy.service;

import dev.gui.data_fy.client.AlbumSpotifyClient;
import dev.gui.data_fy.client.ArtistSpotifyClient;
import dev.gui.data_fy.client.AuthSpotifyClient;
import dev.gui.data_fy.model.Album;
import dev.gui.data_fy.model.LoginRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyService {
    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;
    private final ArtistSpotifyClient artistSpotifyClient;

    public SpotifyService(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient, ArtistSpotifyClient artistSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
        this.artistSpotifyClient = artistSpotifyClient;
    }

    //Token de acesso para realizar as funções
    public String getAcessToken(String clientId, String clientSecret) {
        var request = new LoginRequest("client_credentials", clientId, clientSecret);
        return authSpotifyClient.login(request).getAccessToken();
    }

    public List<Album> getNewReleases(String clientId, String clientSecret) {

        var token = authSpotifyClient.login(request).getAccessToken();
        var response = albumSpotifyClient.getReleases("Bearer " + token);
        return response.getAlbums().getItems();
    }
}
