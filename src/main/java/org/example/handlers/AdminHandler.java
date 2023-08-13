package org.example.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.MainApp;
import org.example.repositories.Claim;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AdminHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        if ("GET".equalsIgnoreCase(method)) {
            StringBuilder response = new StringBuilder();

            response.append("<h1>Submitted Claims</h1>");

            for (Claim claim : MainApp.claimsList) {
                response.append("<p>").append(claim).append("</p>");
            }

            byte[] responseBytes = response.toString().getBytes();

            httpExchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(responseBytes);
            os.close();
        } else {
            // Obsługa głównego widoku panelu administracyjnego
            try (InputStream is = this.getClass().getResourceAsStream("/admin.html")) {
                if (is == null) {
                    httpExchange.sendResponseHeaders(404, 0); // Nie znaleziono pliku
                    httpExchange.close();
                    return;
                }

                byte[] fileBytes = is.readAllBytes();

                httpExchange.sendResponseHeaders(200, fileBytes.length);
                OutputStream os = httpExchange.getResponseBody();
                os.write(fileBytes);
                os.close();
            }
        }
    }
}