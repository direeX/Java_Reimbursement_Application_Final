package org.example.models;

import org.example.interfaces.UserActions;
import org.example.utils.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User implements UserActions {
    private List<Receipt> receipts = new ArrayList<>();
    private BigDecimal carUsage;   // Używane do przechowywania odległości przejechanej samochodem
    private LocalDate travelDate;

    private static final BigDecimal CAR_MILEAGE_RATE = new BigDecimal("0.3");  // przykład: 0.3$/km
    private static final int DAILY_ALLOWANCE_RATE = 15;  // przykład: 15$/dzień

    @Override
    public boolean addReceipt(ReceiptType type, BigDecimal amount) {
        if (!Validator.validateReceiptAmount(amount)) {
            return false;
        }
        Receipt receipt = new Receipt(type, amount);
        receipts.add(receipt);
        return true;
    }

    @Override
    public double claimDailyAllowance(int days) {
        if (!Validator.validateDays(days)) {
            throw new IllegalArgumentException("Invalid number of days provided");
        }
        return days * DAILY_ALLOWANCE_RATE;
    }

    @Override
    public boolean claimForCarUsage(BigDecimal distance) {
        if (!Validator.validateDistance(distance)) {
            return false;
        }
        this.carUsage = distance;
        return true;
    }

    @Override
    public double calculateTotalReimbursement() {
        BigDecimal totalReceiptAmount = receipts.stream()
                .map(Receipt::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCarUsageAmount = CAR_MILEAGE_RATE.multiply(carUsage);

        return totalReceiptAmount.add(totalCarUsageAmount).doubleValue();
    }

    // Setter i getter dla travelDate
    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }
}