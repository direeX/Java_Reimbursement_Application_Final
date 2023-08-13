package org.example.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.example.MainApp.dailyAllowanceRate;
import static org.example.MainApp.mileageRate;

public class UserHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if ("GET".equalsIgnoreCase(httpExchange.getRequestMethod())) {
            serveHtml(httpExchange);
        } else if ("POST".equalsIgnoreCase(httpExchange.getRequestMethod())) {
            processFormData(httpExchange);
        }
    }

    private void serveHtml(HttpExchange httpExchange) throws IOException {
        try (InputStream is = this.getClass().getResourceAsStream("/user.html")) {
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

    private void processFormData(HttpExchange httpExchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String formData = br.readLine();

        // Rozdziel przesłane dane formularza
        Map<String, String> params = parseFormData(formData);
        System.out.println(formData); // test
        double days = Double.parseDouble(params.getOrDefault("days", "0"));
        double distance = Double.parseDouble(params.getOrDefault("distance", "0"));

        // ... obliczenia oparte na globalnych stawkach ...
        double reimbursementAmount = 0.0;
        reimbursementAmount += days * dailyAllowanceRate;
        reimbursementAmount += distance * mileageRate;

        String response = "Total Reimbursement Amount: " + reimbursementAmount + "$";
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Map<String, String> parseFormData(String formData) {
        Map<String, String> parameters = new HashMap<>();
        if (formData == null || formData.trim().isEmpty()) {
            return parameters;
        }

        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            parameters.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8),
                    URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8));
        }

        return parameters;
    }
}