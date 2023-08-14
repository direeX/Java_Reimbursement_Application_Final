package org.example.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.config.AppConfig;
import org.example.repositories.Claim;
import org.example.repositories.ReceiptRepository;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class SubmitHandler implements HttpHandler {
    private ReceiptRepository repository = new ReceiptRepository();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            // Oto twoje dane formularza, teraz możesz je przetworzyć
            System.out.println(formData);
            repository.addReceipt(formData);

            Map<String, String> parameters = parseFormData(formData);

            Claim claim = new Claim();
            claim.setTripDate(parameters.get("trip-date"));
            claim.setReceiptType(parameters.get("receipt-type"));
            claim.setDays(Integer.parseInt(parameters.getOrDefault("days", "0")));
            claim.setDisableDays(Boolean.parseBoolean(parameters.get("disable-days")));
            claim.setDistance(Integer.parseInt(parameters.getOrDefault("distance", "0")));

            AppConfig.addClaim(claim);

            double reimbursementAmount = 0.0;
            reimbursementAmount += claim.getDays() * AppConfig.getDailyAllowanceRate();
            reimbursementAmount += claim.getDistance() * AppConfig.getMileageRate();

            String response = "Total Reimbursement Amount: " + reimbursementAmount + "$";

            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> parameters = new HashMap<>();
        if (formData != null && !formData.isEmpty()) {
            String[] pairs = formData.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8") : "";
                parameters.put(URLDecoder.decode(keyValue[0], "UTF-8"), value);
            }
        }
        return parameters;
    }
}