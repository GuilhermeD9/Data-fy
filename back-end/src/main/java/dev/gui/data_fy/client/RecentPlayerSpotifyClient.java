package dev.gui.data_fy.client;

import dev.gui.data_fy.model.RecentTracks;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "spotifyClient",
        url = "https://api.spotify.com/v1")
public interface RecentPlayerSpotifyClient {
    List<RecentTracks> getRecentlyPlayedTracks(
            @RequestHeader("Authorization") String acessToken,
            @RequestParam(value = "limit", defaultValue = "20") int limit
    );
}
