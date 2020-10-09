package com.advisor;


import com.advisor.web.WebServer;
import com.advisor.web.message.*;
import com.advisor.web.spotify.SpotifyController;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Dionysios Stolis 10/4/2020 <dstolis@b-open.com>
 */
class Advisor {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String NEW = "new";
    private static final String FEATURED = "featured";
    private static final String CATEGORIES = "categories";
    private static final String PLAYLISTS_MOOD = "playlists Mood";
    private static final String EXIT = "exit";
    private static final String AUTH = "auth";
    private final static String authUrl = "/authorize?client_id=c3d20ba42c934384b0188215ca77eb66&redirect_uri=http://localhost:8888&response_type=code";
    private final static SpotifyController controller = new SpotifyController();

    protected void menu(String... args) throws IOException, InterruptedException {
        String option = scanner.nextLine();
        String content;
        Message message;
        if (args.length > 1) {
            controller.setUp(args[1], "");
        }
        switch (option) {
            case NEW:
                if (controller.isAuthorized()) {
                    content = "Mountains [Sia, Diplo, Labrinth]\n" +
                            "Runaway [Lil Peep]\n" +
                            "The Greatest Show [Panic! At The Disco]\n" +
                            "All Out Life [Slipknot]";
                    message = new NewMessage(content);
                    message.print();
                } else {
                    Message.printErrorMessage();

                }
                menu();
            case FEATURED:
                if (controller.isAuthorized()) {
                    content = "Mellow Morning\n" +
                            "Wake Up and Smell the Coffee\n" +
                            "Monday Motivation\n" +
                            "Songs to Sing in the Shower";
                    message = new FeaturedMessage(content);
                    message.print();
                } else {
                    Message.printErrorMessage();
                }
                menu();
            case CATEGORIES:
                if (controller.isAuthorized()) {
                    content = "Top Lists\n" +
                            "Pop\n" +
                            "Mood\n" +
                            "Latin";
                    message = new CategoryMessage(content);
                    message.print();
                } else {
                    Message.printErrorMessage();
                }
                menu();
            case PLAYLISTS_MOOD:
                if (controller.isAuthorized()) {
                    content = "Walk Like A Badass  \n" +
                            "Rage Beats  \n" +
                            "Arab Mood Booster  \n" +
                            "Sunday Stroll";
                    message = new MoodMessage(content);
                    message.print();
                } else {
                    Message.printErrorMessage();
                }
                menu();
            case AUTH:
                auth();
                System.out.println("---SUCCESS---");
                menu();
            case EXIT:
                System.out.println("---GOODBYE!---");
                System.exit(-1);
            default:
                break;
        }
    }

    private void auth() throws IOException, InterruptedException {
        WebServer server = new WebServer();
        server.start();
        System.out.println("use this link to request the access code:");
        System.out.println(controller.getSpotifyAccountUrl() + authUrl);
        System.out.println("waiting for code...");
        while (!server.isCodeReceive()) {
            Thread.sleep(3);
        }
        System.out.println(server.getCode());
        controller.auth(server.getCode());
    }
}
