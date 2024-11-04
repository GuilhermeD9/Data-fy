package dev.gui.data_fy.controller;

import dev.gui.data_fy.model.Album;
import dev.gui.data_fy.model.Artist;
import dev.gui.data_fy.model.RecentTracks;
import dev.gui.data_fy.model.Track;
import dev.gui.data_fy.service.LoginService;
import dev.gui.data_fy.service.SpotifyService;
import feign.FeignException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spotify/api")
public class SpotifyController {
    private final SpotifyService spotifyService;
    private final LoginService loginService;

    @Autowired
    public SpotifyController(SpotifyService spotifyService, LoginService loginService) {
        this.spotifyService = spotifyService;
        this.loginService = loginService;
    }

    @GetMapping("/authorize")
    public void authorize(HttpServletResponse response) throws IOException {
        loginService.authorizeUser(response);
    }

    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam("code") String code, HttpSession session) {
        //Trocar o código de autorização por um token de acesso
        try {
            String acessToken = loginService.handleCallback(code, session);
            return ResponseEntity.ok("<h1>Autenticado com sucesso!</h1> Pode fechar essa pagina e retornar a página anterior.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Erro durante a autenticação: " + e.getMessage());
        }
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getNewAlbums(HttpSession session) {
        String accessToken = loginService.getAcessToken(session);
        List<Album> albums = spotifyService.getNewReleases(accessToken);
        return ResponseEntity.ok(albums);
    }

    @GetMapping("top-world-tracks")
    public ResponseEntity<List<Track>> getTopWorldTracks(HttpSession session) {
        String accessToken = loginService.getAcessToken(session);
        List<Track> tracks = spotifyService.getTopWorldTracks(accessToken);
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("/recent-tracks")
    public ResponseEntity<?> getRecentlyPlayedTracks(HttpSession session){
        try {
            List<RecentTracks> recentTracks = spotifyService.getRecentlyPlayedTracks(session);
            return ResponseEntity.ok(recentTracks);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body(Map.of("error", "Unauthorized: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed do retrieve top user tracks: " + e.getMessage()));
        }
    }

    @GetMapping("/top-user-artists")
    public ResponseEntity<?> getTopUserArtists(HttpSession session) {
        try {
            List<Artist> artists = spotifyService.getTopUserArtists(session);
            return ResponseEntity.ok(artists);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body(Map.of("error", "Unauthorized: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed do retrieve top user tracks: " + e.getMessage()));
        }
    }

    @GetMapping("/top-user-tracks")
    public ResponseEntity<?> getTopUserTracks(HttpSession session) {
        try {
            List<Track> tracks = spotifyService.getTopUserTracks(session);
            return ResponseEntity.ok(tracks);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body(Map.of("error", "Unauthorized: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed do retrieve top user tracks: " + e.getMessage()));
        }
    }

    @GetMapping("/top-user-genres")
    public ResponseEntity<Map<String, Integer>> getTopGenres(HttpSession session) {
        Map<String, Integer> topGenres = spotifyService.getTopGenres(session);
        return ResponseEntity.ok(topGenres);
    }
}
