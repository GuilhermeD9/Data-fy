package dev.gui.data_fy.controller;

import dev.gui.data_fy.client.GoogleClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final GoogleClient googleClient;

    public AlbumController(GoogleClient googleClient) {
        this.googleClient = googleClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok(googleClient.helloWorld());
    }
}
