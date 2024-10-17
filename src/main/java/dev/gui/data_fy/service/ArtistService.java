package dev.gui.data_fy.service;

import dev.gui.data_fy.client.ArtistSpotifyClient;

public class ArtistService {
    private final ArtistSpotifyClient artistSpotifyClient;

    public ArtistService(ArtistSpotifyClient artistSpotifyClient) {
        this.artistSpotifyClient = artistSpotifyClient;
    }

    public List<Artist> getTopUserArtists(String token) {
        var response = artistSpotifyClient.getTopUserArtists("Bearer " + token);
        return response.getItems();
    }
}
