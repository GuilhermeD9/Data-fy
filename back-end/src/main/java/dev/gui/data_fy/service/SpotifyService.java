package dev.gui.data_fy.service;

import dev.gui.data_fy.client.AlbumSpotifyClient;
import dev.gui.data_fy.client.ArtistSpotifyClient;
import dev.gui.data_fy.client.RecentPlayerSpotifyClient;
import dev.gui.data_fy.client.TrackSpotifyClient;
import dev.gui.data_fy.model.Album;
import dev.gui.data_fy.model.Artist;
import dev.gui.data_fy.model.RecentTracks;
import dev.gui.data_fy.model.Track;
import feign.FeignException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpotifyService {
    private final AlbumSpotifyClient albumSpotifyClient;
    private final ArtistSpotifyClient artistSpotifyClient;
    private final TrackSpotifyClient trackSpotifyClient;
    private final RecentPlayerSpotifyClient recentPlayerSpotifyClient;
    private final LoginService loginService;

    @Autowired
    public SpotifyService(AlbumSpotifyClient albumSpotifyClient, ArtistSpotifyClient artistSpotifyClient, TrackSpotifyClient trackSpotifyClient, RecentPlayerSpotifyClient recentPlayerSpotifyClient, LoginService loginService) {
        this.albumSpotifyClient = albumSpotifyClient;
        this.artistSpotifyClient = artistSpotifyClient;
        this.trackSpotifyClient = trackSpotifyClient;
        this.recentPlayerSpotifyClient = recentPlayerSpotifyClient;
        this.loginService = loginService;
    }

    //Novos albuns do spotify
    public List<Album> getNewReleases(String token) {
        var response = albumSpotifyClient.getReleases("Bearer " + token);
        return response.albums().items();
    }

    //Músicas tocadas recentemente
    public List<RecentTracks> getRecentlyPlayedTracks(HttpSession session) {
        String token = loginService.getAcessToken(session);
        try {
            var response = recentPlayerSpotifyClient.getRecentlyPlayedTracks("Bearer " + token, 20);
            return response.items();
        } catch (FeignException e) {
            if (e.status() == 401) {
                System.err.println("Token inválido ou expirado");
            }
            throw e;
        }
    }

    //Artistas mais escutados do usuário
    public List<Artist> getTopUserArtists(HttpSession session) {
        int limit = 15;
        int offset = 0;
        String token = loginService.getAcessToken(session);
        try {
            var response = artistSpotifyClient.getTopUserArtists("Bearer " + token, limit, offset);
            return response.items();
        } catch (FeignException e) {
            if (e.status() == 401) {
                System.err.println("Token inválido ou expirado");
            }
            throw e;
        }
    }

    //Músicas mais ouvidas do usuário
    public List<Track> getTopUserTracks(HttpSession session) {
        int limit = 15;
        int offset = 0;
        String token = loginService.getAcessToken(session);
        try {
            var response = trackSpotifyClient.getTopUserTracks("Bearer " + token, limit, offset);
            return response.items();
        } catch (FeignException e) {
            if (e.status() == 401) {
                System.err.println("Token inválido ou expirado.");
            }
            throw e;
        }
    }

    public Map<String, Integer> getTopGenres(HttpSession session) {
        int limit = 15;
        int offset = 0;
        String token = loginService.getAcessToken(session);

        Map<String, Integer> genreCount = new HashMap<>();

        try {
            List<Artist> topArtists = artistSpotifyClient.getTopUserArtists("Bearer " + token, limit, offset).items();

            for (Artist artist : topArtists) {
                for (String genre : artist.genres()) {
                    genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
                }
            }

            return genreCount.entrySet().stream()
                    .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                    .limit(15)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));
        } catch (FeignException e) {
            if (e.status() == 401) {
                System.err.println("Token inválido ou expirado");
            }
            throw e;
        }
    }
}
