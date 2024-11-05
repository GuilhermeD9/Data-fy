package dev.gui.data_fy.client;

import dev.gui.data_fy.model.TopPlaylistTrackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "TopPlaylistSpotifyClient",
        url = "https://api.spotify.com"
)
public interface TopPlaylistSpotifyClient {
    @GetMapping("/v1/playlists/{playlistId}/tracks")
    TopPlaylistTrackResponse getTopTracksPlaylist(@RequestHeader("Authorization") String token,
                                                  @PathVariable("playlistId") String playlistId,
                                                  @RequestParam("limit") int limit);
}
