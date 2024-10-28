package dev.gui.data_fy.client;

import dev.gui.data_fy.model.ArtistResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ArtistSpotifyClient",
    url = "https://api.spotify.com")
public interface ArtistSpotifyClient {
    @GetMapping(value = "/v1/me/top/artists")
    ArtistResponse getTopUserArtists(@RequestHeader("Authorization") String authorization);
}
