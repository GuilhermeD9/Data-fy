package dev.gui.data_fy.service;

import dev.gui.data_fy.client.AlbumSpotifyClient;
import dev.gui.data_fy.client.ArtistSpotifyClient;
import dev.gui.data_fy.client.AuthSpotifyClient;
import dev.gui.data_fy.model.Album;
import dev.gui.data_fy.model.Artist;
import dev.gui.data_fy.model.LoginRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class SpotifyService {
    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;
    private final ArtistSpotifyClient artistSpotifyClient;

    //Credenciais
    private String clientId = "e7b49745a69b45059d31033e2638e8c1";
    private String clientSecret = "1de1a2bc971941218785ddc0ca320c2d";
    private String redirectUri = "http://localhost:8080/spotify/api/callback";

    public SpotifyService(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient, ArtistSpotifyClient artistSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
        this.artistSpotifyClient = artistSpotifyClient;
    }

    //Recupera o token de acesso da sessão ou solicita um novo token
    public String getAcessToken(HttpSession session) {
        String accessToken = (String) session.getAttribute("accessToken");
        if (accessToken == null) {
            accessToken = requestClientCredentialsToken();
            storeAccessToken(session, accessToken);
        }
        return accessToken;
    }

    //Solicitar um novo token usando client_credentials
    private String requestClientCredentialsToken() {
        var request = new LoginRequest("client_credentials", clientId, clientSecret);
        return authSpotifyClient.login(request).getAccessToken();
    }

    //Armazenar o token de acesso
    public void storeAccessToken(HttpSession session, String accessToken) {
        session.setAttribute("accessToken", accessToken);
    }

    //Método para pedir autorização dos dados do usuário
    public void authorizeUser(HttpServletResponse response) throws IOException {
        String scopes = "user-top-read"; //Permissões necessárias

        //URL de redirecionamento do usuário
        String url = "https://accounts.spotify.com/authorize"
                + "?client_id=" + clientId
                + "&response_type=code"
                + "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8)
                + "&scope=" + URLEncoder.encode(scopes, StandardCharsets.UTF_8);

        response.sendRedirect(url);
    }

    //Trocar o código de autorização por um token de acesso
    public String handleCallback(String code, HttpSession session) {
        var request = new LoginRequest("authorization_code", code, clientId, clientSecret, redirectUri);
        String accessToken = authSpotifyClient.login(request).getAccessToken();
        storeAccessToken(session, accessToken);
        return accessToken;
    }

    //Novos albuns do spotify
    public List<Album> getNewReleases(String token) {
        var response = albumSpotifyClient.getReleases("Bearer " + token);
        return response.getAlbums().getItems();
    }

    //Artistas mais escutados do usuário
    public List<Artist> getTopUserArtists(String token) {
        var response = artistSpotifyClient.getTopUserArtists("Bearer " + token);
        return response.getItems();
    }

    //Token para o usuário externo
    public String exchangeCodeForAcessToken(String code, String clientId, String clientSecret) {
        var request = new LoginRequest("authorization_code", code, clientId, clientSecret, redirectUri);
        return authSpotifyClient.login(request).getAccessToken();
    }

}
