package dev.gui.data_fy.client;

import dev.gui.data_fy.model.TrackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "TrackSpotifyClient",
        url = "https://api.spotify.com")
public interface TrackSpotifyClient {
    @GetMapping(value = "/v1/me/top/tracks")
    TrackResponse getTopUserTracks(@RequestHeader("Authorization") String authorization,
                                   @RequestParam("limit") int limit);
}
