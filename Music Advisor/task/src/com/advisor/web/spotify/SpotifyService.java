package com.advisor.web.spotify;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

/**
 * @author Dionysios Stolis 10/5/2020 <dstolis@b-open.com>
 */
public class SpotifyService {

    private String spotifyAccountUrl = "https://accounts.spotify.com";
    private String spotifyApiUrl = "https://api.spotify.com";
    private boolean isAuth = false;
    private final HttpClient httpClient = HttpClient.newBuilder().build();

    public void setUp(String accountUrl, String apiUrl) {
        if (!spotifyAccountUrl.isEmpty()) {
            this.spotifyAccountUrl = accountUrl;
        }
        if (!spotifyApiUrl.isEmpty()) {
            this.spotifyApiUrl = apiUrl;
        }
    }

    public boolean isAuth() {
        return isAuth;
    }

    public String getAccountUrl() {
        return spotifyAccountUrl;
    }

    public String getApiUrl() {
        return spotifyApiUrl;
    }

    public void authorize(String code) {
        String uri = spotifyAccountUrl + "/api/token";

        System.out.println("code received\n" +
                "making http request for access_token...");

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", " Basic " + Base64.getEncoder().encodeToString(("c3d20ba42c934384b0188215ca77eb66:e18c62ef440b4005a7ea0d122c7a6196").getBytes()))
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code&code=" + code + "&redirect_uri=http://localhost:8888"))
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
//            token = gson.fromJson(response.body(), Token.class);

            System.out.println("response:");
//            System.out.println(token.getAccessToken());
            System.out.println(response.body());
            isAuth = true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
