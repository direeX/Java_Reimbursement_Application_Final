package org.example;



import com.sun.net.httpserver.HttpServer;
import org.example.handlers.AdminHandler;
import org.example.handlers.MainHandler;
import org.example.handlers.UserHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MainApp {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/", new MainHandler());
            server.createContext("/user", new UserHandler());
            server.createContext("/admin", new AdminHandler());
            server.setExecutor(null); // tworzy domyślny executor
            server.start();
            System.out.println("Server started on port 8080");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}