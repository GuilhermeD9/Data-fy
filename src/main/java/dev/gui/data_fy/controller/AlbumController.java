package dev.gui.data_fy.controller;

import dev.gui.data_fy.client.*;
import dev.gui.data_fy.model.Album;
import dev.gui.data_fy.model.LoginRequest;
import dev.gui.data_fy.service.SpotifyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {
    private final SpotifyService spotifyService;

    public AlbumController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getNewAlbums() {
        List<Album> albums = spotifyService.getNewReleases("e7b49745a69b45059d31033e2638e8c1",
                "1de1a2bc971941218785ddc0ca320c2d");
        return ResponseEntity.ok(albums);
    }
}
