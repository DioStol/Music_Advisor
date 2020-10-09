package com.advisor.web.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Dionysios Stolis 6/24/2020 <dstolis@b-open.com>
 */
public class AuthorizationController {

    private static final String CLIENT_ID = "c3d20ba42c934384b0188215ca77eb66";

    static final String CLIENTID = "c3d20ba42c934384b0188215ca77eb66";
    static final String CLIENTSECRET = "c3d20ba42c934384b0188215ca77eb66";
    static final String REDIRECTURL = "https://localhost:8080/v1"; //whiltelisted set inside spotify

    public void test() {
        try {

            String url_auth =
                    "https://accounts.spotify.com/authorize?"
                            + "client_id=" + CLIENTID + "&"
                            + "response_type=code&"
                            + "redirect_uri=" + REDIRECTURL;

            System.out.println(url_auth);

            URL url = new URL(url_auth);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

         //   conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean auth(){
        String url_auth =
                "https://accounts.spotify.com/authorize?"
                        + "client_id=" + CLIENTID + "&"
                        + "response_type=code&"
                        + "redirect_uri=" + REDIRECTURL;

        URL url;
        try {
            url = new URL(url_auth);
            System.out.println(url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            boolean isOk =  conn.getResponseCode() == 200;
            conn.disconnect();
            return isOk;
        } catch (IOException e) {
            return false;
        }
    }

}
