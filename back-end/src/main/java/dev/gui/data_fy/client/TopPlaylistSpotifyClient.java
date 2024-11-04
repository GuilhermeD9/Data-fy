package dev.gui.data_fy.client;

import dev.gui.data_fy.model.TrackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "TopPlaylistSpotifyClient",
        url = "https://api.spotify.com/v1"
)
public interface TopPlaylistSpotifyClient {
    @GetMapping("/playlists/{playlistId}/tracks")
    TrackResponse getTopTracksPlaylist(@RequestHeader("Authorization") String token,
                                       @PathVariable("playlistId") String playlistId);
}
