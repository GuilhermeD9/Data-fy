package dev.gui.data_fy.controller;

import dev.gui.data_fy.model.Album;
import dev.gui.data_fy.model.Artist;
import dev.gui.data_fy.service.SpotifyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
