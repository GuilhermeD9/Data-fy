package dev.gui.data_fy.controller;

import dev.gui.data_fy.model.Album;
import dev.gui.data_fy.model.Artist;
import dev.gui.data_fy.service.SpotifyService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/spotify/api")
public class SpotifyController {
    private final SpotifyService spotifyService;
    private String token;

    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    private String getAcessToken() {
        return spotifyService.getAcessToken();
    }

    @GetMapping("/authorize")
    public void authorize(HttpServletResponse response) throws IOException {
        spotifyService.authorizeUser(response);
    }

    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam("code") String code) {
        //Trocar o código de autorização por um token de acesso
        String acessToken = spotifyService.handleCallback(code);
        return ResponseEntity.ok("Autenticado com sucesso! Token: " + acessToken);
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getNewAlbums() {
        List<Album> albums = spotifyService.getNewReleases(getAcessToken());
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/top-user-artists")
    public ResponseEntity<List<Artist>> getTopUserArtists() {
        List<Artist> artists = spotifyService.getTopUserArtists(getAcessToken());
        return ResponseEntity.ok(artists);
    }

}
