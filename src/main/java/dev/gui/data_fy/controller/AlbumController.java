package dev.gui.data_fy.controller;

import dev.gui.data_fy.client.AuthSpotifyClient;
import dev.gui.data_fy.client.GoogleClient;
import dev.gui.data_fy.client.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<String> helloWorld() {
        var request = new LoginRequest("client_credentials",
                "e7b49745a69b45059d31033e2638e8c1",
                "1de1a2bc971941218785ddc0ca320c2d");
        return ResponseEntity.ok(authSpotifyClient.login(request).getAccessToken());
    }
}
