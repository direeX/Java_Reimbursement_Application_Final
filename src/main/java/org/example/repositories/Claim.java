package org.example.repositories;

import org.example.config.AppConfig;

public class Claim {
    private String tripDate;
    private String receiptType;
    private int days;
    private int distance;

    public Claim(String tripDate, String receiptType, int days, int distance) {
        this.tripDate = tripDate;
        this.receiptType = receiptType;
        this.days = days;
        this.distance = distance;
    }

    public Claim() {

    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getReimbursementAmount() {
        double total = 0.0;
        total += this.getDays() * AppConfig.getDailyAllowanceRate();
        total += this.getDistance() * AppConfig.getMileageRate();
        return total;
    }

    @Override
    public String toString() {
        return "Date of trip: " + tripDate +
                ", Receipt type: " + receiptType +
                ", Days: " + days +
                ", Distance: " + distance;
    }
}
