package org.example.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.config.AppConfig;
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
            for (Claim claim : AppConfig.getClaimsList()) {
                response.append("<details>");
                response.append("<summary>").append(claim.getTripDate()).append(" - ").append(claim.getReceiptType()).append("</summary>");
                response.append("Data podróży: <input type='text' name='tripDate' value='").append(claim.getTripDate()).append("'><br>");
                response.append("Typ paragonu: <input type='text' name='receiptType' value='").append(claim.getReceiptType()).append("'><br>");
                response.append("Ilość dni: <input type='text' name='days' value='").append(claim.getDays()).append("'><br>");
//                response.append("Niepełnosprawność: <input type='checkbox' name='isDisabled'").append(claim.isDisableDays() ? " checked" : "").append("><br>");
                response.append("Dystans: <input type='text' name='distance' value='").append(claim.getDistance()).append("'> km<br>");
                response.append("<button onclick='submitEdit(this)'>Zapisz zmiany</button>");
                response.append("</details>");
            }
            response.append("</div>");

            response.append("<div class=\"ratesContainer\">");
            response.append("Stawka za dzień: <input type='text' name='dailyRate' value='").append(AppConfig.getDailyAllowanceRate()).append("'><br>");
            response.append("Stawka za km: <input type='text' name='mileageRate' value='").append(AppConfig.getMileageRate()).append("'><br>");
            response.append("<button onclick='submitRates(this)'>Zapisz stawki</button>");
            response.append("</div>");

            byte[] responseBytes = response.toString().getBytes();

            httpExchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(responseBytes);
            os.close();
        } else if ("POST".equalsIgnoreCase(method)) {
            // Odczytuj dane wniosku wysłane z front-endu
            InputStream reqBody = httpExchange.getRequestBody();
            byte[] bodyBytes = reqBody.readAllBytes();
            String bodyStr = new String(bodyBytes, "utf-8");

            System.out.println("Received POST data: " + bodyStr);

            String[] pairs = bodyStr.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length != 2) continue;

                String key = keyValue[0];
                String value = keyValue[1];

                if ("dailyRate".equals(key)) {
                    try {
                        double rate = Double.parseDouble(value);
                        AppConfig.setDailyAllowanceRate(rate);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else if ("mileageRate".equals(key)) {
                    try {
                        double rate = Double.parseDouble(value);
                        AppConfig.setMileageRate(rate);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }

            boolean operacjaUdane = true;

            httpExchange.getResponseHeaders().set("Content-Type", "text/plain");
            OutputStream os = httpExchange.getResponseBody();
            if (operacjaUdane) {
                byte[] responseBytes = "success".getBytes();
                httpExchange.sendResponseHeaders(200, responseBytes.length);
                os.write(responseBytes);
            } else {
                byte[] responseBytes = "error".getBytes();
                httpExchange.sendResponseHeaders(200, responseBytes.length);
                os.write(responseBytes);
            }
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