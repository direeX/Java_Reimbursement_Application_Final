package org.example.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        byte[] response;
        if ("/".equals(path)) {
            response = loadResource("index.html");
            exchange.sendResponseHeaders(200, response.length);
        } else {
            try {
                response = loadResource(path.substring(1));
                exchange.sendResponseHeaders(200, response.length);
            } catch (FileNotFoundException e) {
                response = "404 Not Found".getBytes();
                exchange.sendResponseHeaders(404, response.length);
            }
        }
        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }

    private byte[] loadResource(String resourceName) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName);
        if (is == null) {
            throw new FileNotFoundException("File " + resourceName + " was not found.");
        }
        return is.readAllBytes();
    }
}