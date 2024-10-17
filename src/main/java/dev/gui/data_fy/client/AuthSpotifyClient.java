package dev.gui.data_fy.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "AuthSpotifyClient", url = "https://accounts.spotify.com"
)
public interface AuthSpotifyClient {
}
