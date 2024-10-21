package dev.gui.data_fy.service;

import dev.gui.data_fy.client.AuthSpotifyClient;
import dev.gui.data_fy.model.LoginResponse;
import feign.FeignException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class LoginService {
    private final AuthSpotifyClient authSpotifyClient;
    private String clientId = System.getenv("SPOTIFY_CLIENT_ID");
    private String clientSecret = System.getenv("SPOTIFY_CLIENT_SECRET");
    private String redirectUri = "http://localhost:8080/spotify/api/callback";

    @Autowired
    public LoginService(AuthSpotifyClient authSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
    }

    public void verificator() {
        if (clientId == null || clientSecret == null) {
            throw new IllegalArgumentException("Variáveis de ambiente não configuradas corretamente.");
        }
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
        verificator();
        String authHeader = getBasicAuthHeader();
        try {
            return authSpotifyClient.login(authHeader,
                    "client_credentials",
                    null,
                    null,
                    clientId,
                    clientSecret).getAccessToken();
        } catch (FeignException e) {
            System.err.println("Error during client credentials authentication " + e.getMessage());
            System.err.println("Response body: " + e.contentUTF8());
            throw e;
        }
    }

    //Armazenar o token de acesso
    public void storeAccessToken(HttpSession session, String accessToken) {
        session.setAttribute("accessToken", accessToken);
    }

    //Método para pedir autorização dos dados do usuário
    public void authorizeUser(HttpServletResponse response) throws IOException {
        verificator();
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
        verificator();
        String authHeader = getBasicAuthHeader();
        try {
            LoginResponse response = authSpotifyClient.login(
                    authHeader, "authorization_code",
                    code,
                    redirectUri,
                    clientId,
                    clientSecret
            );
            return response.getAccessToken();
        } catch (FeignException e) {
            System.err.println("Error during authentication: " + e.getMessage());
            System.err.println("Response body: " + e.contentUTF8());
            throw e;
        }
    }

    //Cria o cabeçalho básico para login
    private String getBasicAuthHeader() {
        String auth = clientId + ":" + clientSecret;
        return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
    }
}