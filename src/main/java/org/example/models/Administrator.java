package org.example.models;

import org.example.interfaces.AdminActions;
import org.example.utils.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Administrator implements AdminActions {

    private BigDecimal dailyAllowanceRate = new BigDecimal("15");  // $15 per day
    private BigDecimal mileageRate = new BigDecimal("0.3");  // $0.3 per km

    private Map<ReceiptType, BigDecimal> receiptLimits = new HashMap<>();

    @Override
    public boolean setDailyAllowanceRate(BigDecimal rate) {
        if (!Validator.validateDailyAllowanceRate(rate)) {
            return false;
        }
        this.dailyAllowanceRate = rate;
        return true;
    }

    @Override
    public boolean setMileageRate(BigDecimal rate) {
        if (!Validator.validateMileageRate(rate)) {
            return false;
        }
        this.mileageRate = rate;
        return true;
    }

    @Override
    public boolean addReceiptType(ReceiptType type) {
        return false;
    }

    @Override
    public boolean removeReceiptType(ReceiptType type) {
        return false;
    }

    @Override
    public boolean setReceiptLimit(ReceiptType type, BigDecimal limit) {
        if (!Validator.validateReceiptLimit(limit)) {
            return false;
        }
        this.receiptLimits.put(type, limit);
        return true;
    }

    // Getters, jeśli są potrzebne

    public BigDecimal getDailyAllowanceRate() {
        return dailyAllowanceRate;
    }

    public BigDecimal getMileageRate() {
        return mileageRate;
    }

    public BigDecimal getReceiptLimit(ReceiptType type) {
        return receiptLimits.get(type);
    }
}