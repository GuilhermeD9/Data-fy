package dev.gui.data_fy.service;

import dev.gui.data_fy.client.AlbumSpotifyClient;
import dev.gui.data_fy.client.ArtistSpotifyClient;
import dev.gui.data_fy.client.AuthSpotifyClient;
import dev.gui.data_fy.model.Album;
import dev.gui.data_fy.model.Artist;
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
    public String getAcessToken() {
        var request = new LoginRequest("client_credentials", "e7b49745a69b45059d31033e2638e8c1", "1de1a2bc971941218785ddc0ca320c2d");
        return authSpotifyClient.login(request).getAccessToken();
    }

    //Novos albuns do spotify
    public List<Album> getNewReleases(String token) {
        var response = albumSpotifyClient.getReleases("Bearer " + token);
        return response.getAlbums().getItems();
    }

    //Artistas mais escutados do usuário
    public List<Artist> getTopUserArtists(String token) {
        var response = artistSpotifyClient.getTopUserArtists("Bearer " + token);
        return response.getItems();
    }
}
