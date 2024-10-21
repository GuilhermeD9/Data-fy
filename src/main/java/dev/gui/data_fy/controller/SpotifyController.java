package dev.gui.data_fy.controller;

import dev.gui.data_fy.model.Album;
import dev.gui.data_fy.model.Artist;
import dev.gui.data_fy.service.LoginService;
import dev.gui.data_fy.service.SpotifyService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
        String acessToken = loginService.handleCallback(code, session);
        return ResponseEntity.ok("Autenticado com sucesso! Token: " + acessToken);
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getNewAlbums(HttpSession session) {
        String accessToken = loginService.getAcessToken(session);

        if (accessToken == null) {
            //Redireciona o usuário caso o token não esteja disponível
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Album> albums = spotifyService.getNewReleases(accessToken);
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/top-user-artists")
    public ResponseEntity<List<Artist>> getTopUserArtists(HttpSession session) {
        String accessToken = loginService.getAcessToken(session);

        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Artist> artists = spotifyService.getTopUserArtists(accessToken);
        return ResponseEntity.ok(artists);
    }

}
