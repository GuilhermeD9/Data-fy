package dev.gui.data_fy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        try {
            String acessToken = loginService.handleCallback(code, session);
            return ResponseEntity.ok("Autenticado com sucesso! Token: " + acessToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Erro durante a autenticação: " + e.getMessage());
        }
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
    public void getTopUserArtists(HttpSession session, HttpServletResponse response) throws IOException {
        String accessToken = loginService.getAcessToken(session);

        if (accessToken == null) {
            loginService.authorizeUser(response);
            return;
        }
        System.out.println("Acess token na sessão: " + accessToken);
        try {
            List<Artist> artists = spotifyService.getTopUserArtists(accessToken);
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(artists));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Failed to retrieve top user artists: "
            + e.getMessage() + "\"}");
        }
    }

}
