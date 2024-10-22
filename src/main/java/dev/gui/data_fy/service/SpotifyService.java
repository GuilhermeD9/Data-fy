package dev.gui.data_fy.service;

import dev.gui.data_fy.client.AlbumSpotifyClient;
import dev.gui.data_fy.client.ArtistSpotifyClient;
import dev.gui.data_fy.model.Album;
import dev.gui.data_fy.model.Artist;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpotifyService {
    private final AlbumSpotifyClient albumSpotifyClient;
    private final ArtistSpotifyClient artistSpotifyClient;

    @Autowired
    public SpotifyService(AlbumSpotifyClient albumSpotifyClient, ArtistSpotifyClient artistSpotifyClient) {
        this.albumSpotifyClient = albumSpotifyClient;
        this.artistSpotifyClient = artistSpotifyClient;
    }

    //Novos albuns do spotify
    public List<Album> getNewReleases(String token) {
        var response = albumSpotifyClient.getReleases("Bearer " + token);
        return response.getAlbums().getItems();
    }

    //Artistas mais escutados do usuário
    public List<Artist> getTopUserArtists(String token) {
        try {
            var response = artistSpotifyClient.getTopUserArtists("Bearer " + token);
            return response.getItems();
        } catch (FeignException e) {
            if (e.status() == 401) {
                System.err.println("Token inválido ou expirado");
            }
            throw e;

        }
    }

}
