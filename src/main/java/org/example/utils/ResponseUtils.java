//package org.example.utils;
//
//import com.sun.net.httpserver.HttpExchange;
//
//import java.io.IOException;
//import java.io.OutputStream;
//
//public class ResponseUtils {
//
//    public static void sendOkResponse(HttpExchange exchange, String response) throws IOException {
//        byte[] bytes = response.getBytes();
//        exchange.sendResponseHeaders(200, bytes.length);
//        try (OutputStream os = exchange.getResponseBody()) {
//            os.write(bytes);
//        }
//    }
//
//    // Możesz tu dodać więcej metod pomocniczych do obsługi różnych typów odpowiedzi, jeśli potrzebujesz
//}