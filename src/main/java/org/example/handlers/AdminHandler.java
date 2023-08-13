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
            response.append("<table border='1'>");
            response.append("<thead>");
            response.append("<tr>");
            response.append("<th>Date of trip</th>");
            response.append("<th>Receipt type</th>");
            response.append("<th>Days</th>");
            response.append("<th>Disable days</th>");
            response.append("<th>Distance</th>");
            response.append("</tr>");
            response.append("</thead>");
            response.append("<tbody>");

            for (Claim claim : MainApp.claimsList) {
                response.append("<tr>");
                response.append("<td>").append(claim.getTripDate()).append("</td>");
                response.append("<td>").append(claim.getReceiptType()).append("</td>");
                response.append("<td>").append(claim.getDays()).append("</td>");
                response.append("<td>").append(claim.isDisableDays()).append("</td>");
                response.append("<td>").append(claim.getDistance()).append("</td>");
                response.append("</tr>");
            }

            response.append("</tbody>");
            response.append("</table>");

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