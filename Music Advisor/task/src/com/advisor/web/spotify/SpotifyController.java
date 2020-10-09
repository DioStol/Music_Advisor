package com.advisor.web.spotify;

import java.io.IOException;

/**
 * @author Dionysios Stolis 10/5/2020 <dstolis@b-open.com>
 */
public class SpotifyController {

    private SpotifyService service;

    public SpotifyController() {
        service = new SpotifyService();
    }

    public void auth(String code) {
        service.authorize(code);
    }

    public void setUp(String spotifyAccountUrl, String spotifyApiUrl){
        service.setUp(spotifyAccountUrl, spotifyApiUrl);
    }

    public boolean isAuthorized(){
        return service.isAuth();
    }

    public String getSpotifyAccountUrl(){
        return service.getAccountUrl();
    }
}
