package org.example;



import com.sun.net.httpserver.HttpServer;
import org.example.handlers.MyHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MainApp {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/", new MyHandler());
            server.setExecutor(null); // tworzy domyślny executor
            server.start();
            System.out.println("Server started on port 8080");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}