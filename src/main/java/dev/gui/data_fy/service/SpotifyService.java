package dev.gui.data_fy.service;

import dev.gui.data_fy.client.AlbumSpotifyClient;
import dev.gui.data_fy.client.ArtistSpotifyClient;
import dev.gui.data_fy.model.Album;
import dev.gui.data_fy.model.Artist;
import feign.FeignException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class SpotifyService {
    private final AlbumSpotifyClient albumSpotifyClient;
    private final ArtistSpotifyClient artistSpotifyClient;
    private final LoginService loginService;

    @Autowired
    public SpotifyService(AlbumSpotifyClient albumSpotifyClient, ArtistSpotifyClient artistSpotifyClient, LoginService loginService) {
        this.albumSpotifyClient = albumSpotifyClient;
        this.artistSpotifyClient = artistSpotifyClient;
        this.loginService = loginService;
    }

    //Novos albuns do spotify
    public List<Album> getNewReleases(String token) {
        var response = albumSpotifyClient.getReleases("Bearer " + token);
        return response.getAlbums().getItems();
    }

    //Artistas mais escutados do usuário
    public List<Artist> getTopUserArtists(String token, HttpServletResponse responser, HttpSession session) throws IOException {
        try {
            var response = artistSpotifyClient.getTopUserArtists("Bearer " + token);
            return response.getItems();
        } catch (FeignException e) {
            if (e.status() == 401) {
                System.err.println("Token inválido ou expirado");
                loginService.authorizeUser(responser);
                String newToken = loginService.getAcessToken(session);
                var response2 = artistSpotifyClient.getTopUserArtists("Bearer " + newToken);
                return response2.getItems();
            }
            throw e;
        }
    }
    

}
