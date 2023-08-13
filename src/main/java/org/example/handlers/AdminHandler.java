package org.example.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.models.Receipt;
import org.example.repositories.ReceiptRepository;
import org.example.utils.ResponseUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class AdminHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            // Używaj getResourceAsStream do wczytania zasobu z katalogu resources
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