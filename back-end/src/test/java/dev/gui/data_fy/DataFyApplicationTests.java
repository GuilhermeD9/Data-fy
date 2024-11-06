package dev.gui.data_fy;

import dev.gui.data_fy.client.TopPlaylistSpotifyClient;
import dev.gui.data_fy.service.SpotifyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DataFyApplicationTests {

	@Mock
	private TopPlaylistSpotifyClient topPlaylistSpotifyClient;

	@InjectMocks
	private SpotifyService spotifyService;

	@MockBean
	private SpotifyService spotifyServicie2;

	@Autowired
	private MockMvc mockMvc;


	@Test
	void getTopWorldTracks_ShouldHandleErrorGracefully() {
		// Simula uma exceção da API do Spotify
		Mockito.when(topPlaylistSpotifyClient.getTopTracksPlaylist(anyString(), anyString(), anyInt()))
				.thenThrow(new RuntimeException("API error"));

		assertThrows(RuntimeException.class, () -> spotifyService.getTopRegionTracks("dummyToken", "roma"));
	}

}
