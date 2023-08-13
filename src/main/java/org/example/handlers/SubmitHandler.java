package org.example.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.MainApp;
import org.example.repositories.Claim;
import org.example.repositories.ReceiptRepository;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class SubmitHandler implements HttpHandler {
    private ReceiptRepository repository = new ReceiptRepository(); // lub inny sposób uzyskania dostępu do repozytorium
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            // Oto twoje dane formularza, teraz możesz je przetworzyć
            System.out.println(formData);
            repository.addReceipt(formData); // Dodaj dane formularza do repozytorium

            Map<String, String> parameters = parseFormData(formData);

            Claim claim = new Claim();
            claim.setTripDate(parameters.get("trip-date"));
            claim.setReceiptType(parameters.get("receipt-type"));
            claim.setDays(Integer.parseInt(parameters.getOrDefault("days", "0")));
            claim.setDisableDays(Boolean.parseBoolean(parameters.get("disable-days")));
            claim.setDistance(Integer.parseInt(parameters.getOrDefault("distance", "0")));

            MainApp.claimsList.add(claim);

            String tripDate = parameters.get("trip-date");
            String receiptType = parameters.get("receipts-dropdown");
            int days = Integer.parseInt(parameters.getOrDefault("days", "0"));
            boolean disableDays = parameters.containsKey("disable-days");
            int distance = Integer.parseInt(parameters.getOrDefault("distance", "0"));

            double reimbursementAmount = 0.0;

                reimbursementAmount += days * org.example.MainApp.dailyAllowanceRate;

            reimbursementAmount += distance * org.example.MainApp.mileageRate;

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