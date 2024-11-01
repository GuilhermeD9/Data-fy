package dev.gui.data_fy.client;

import dev.gui.data_fy.model.RecentTracksResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spotifyClient",
        url = "https://api.spotify.com/v1")
public interface RecentPlayerSpotifyClient {
    @GetMapping("/me/player/recently-played")
    RecentTracksResponse getRecentlyPlayedTracks(
            @RequestHeader("Authorization") String acessToken,
            @RequestParam(value = "limit", defaultValue = "20") int limit
    );
}
