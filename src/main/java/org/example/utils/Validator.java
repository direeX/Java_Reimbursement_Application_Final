//package org.example.utils;
//
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//public class Validator {
//
//    public static boolean validateReceiptAmount(BigDecimal amount) {
//        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
//    }
//
//    public static boolean validateDays(int days) {
//        return days > 0;
//    }
//
//    public static boolean validateDistance(BigDecimal distance) {
//        return distance != null && distance.compareTo(BigDecimal.ZERO) > 0;
//    }
//
//    public static boolean validateDailyAllowanceRate(BigDecimal rate) {
//        return rate != null && rate.compareTo(BigDecimal.ZERO) > 0;
//    }
//
//    public static boolean validateMileageRate(BigDecimal rate) {
//        return rate != null && rate.compareTo(BigDecimal.ZERO) > 0;
//    }
//
//    public static boolean validateReceiptLimit(BigDecimal limit) {
//        return limit != null && limit.compareTo(BigDecimal.ZERO) >= 0;
//    }
//
//    // Sprawdza, czy podana wartość dla stawki dziennego dodatku jest poprawna
//    public static boolean validateDailyAllowanceRate(double rate) {
//        return rate > 0;
//    }
//
//    // Sprawdza, czy podana wartość dla stawki za kilometr jest poprawna
//    public static boolean validateMileageRate(double rate) {
//        return rate > 0;
//    }
//
//
//    // Sprawdza, czy podana data jest poprawna (w przeszłości lub dzisiaj)
//    public static boolean validateDate(LocalDate date) {
//        return date != null && (date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now()));
//    }
//}