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

            response.append("<div>");
            for (Claim claim : MainApp.claimsList) {


                response.append("<details>");
                response.append("<summary>").append(claim.getTripDate()).append(" - ").append(claim.getReceiptType()).append("</summary>");
                response.append("Data podróży: <input type='text' value='").append(claim.getTripDate()).append("'><br>");
                response.append("Typ paragonu: <input type='text' value='").append(claim.getReceiptType()).append("'><br>");
                response.append("Ilość dni: <input type='text' value='").append(claim.getDays()).append("'><br>");
                response.append("Niepełnosprawność: <input type='checkbox'").append(claim.isDisableDays() ? " checked" : "").append("><br>");
                response.append("Dystans: <input type='text' value='").append(claim.getDistance()).append("'> km<br>");
                response.append("<button onclick='submitEdit()'>Zapisz zmiany</button>");  // Przycisk do zapisywania zmian
                response.append("</details>");
            }
            response.append("</div>");

            byte[] responseBytes = response.toString().getBytes();

            httpExchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(responseBytes);
            os.close();
        } else {
            // Obsługa głównego widoku panelu administracyjnego
            try (InputStream is = this.getClass().getResourceAsStream("/static/admin.html")) {
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