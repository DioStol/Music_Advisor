package com.advisor.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author Dionysios Stolis 10/4/2020 <dstolis@b-open.com>
 */
public class WebServer {

    HttpServer server;
    String code;
    boolean codeReceive = false;

    public WebServer() throws IOException {
        this.server = HttpServer.create();
        server.bind(new InetSocketAddress(8888), 0);
        server.setExecutor(null);
    }

    public boolean isCodeReceive() {
        return codeReceive;
    }

    public String getCode() {
        return code;
    }

    public void start() {
        server.createContext("/",
                new HttpHandler() {
                    public void handle(HttpExchange exchange) throws IOException {
                        String code = exchange.getRequestURI().getQuery();
                        String message;
                        if (code != null && code.startsWith("code")) {
                            message = "Got the code. Return back to your program.";
                            code = code.replaceFirst("code=", "");
                            setCode(code);
                            codeReceive = true;
                            stop();
                        }else {
                            message = "Not found authorization code. Try again.";
                        }
                        exchange.sendResponseHeaders(200, message.length());
                        exchange.getResponseBody().write(message.getBytes());
                        exchange.close();
                    }
                }
        );
        server.start();
    }

    public void stop() {
        server.stop(0);
        this.codeReceive = true;
    }

    private void setCode(String code){
        this.code = code;
    }
}
