package com.advisor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Advisor musicAdvisor = new Advisor();
        musicAdvisor.menu(args);
    }
}
