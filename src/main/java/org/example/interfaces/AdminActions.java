package org.example.interfaces;


import org.example.models.ReceiptType;

import java.math.BigDecimal;

public interface AdminActions {

    boolean setDailyAllowanceRate(BigDecimal rate);
    boolean setMileageRate(BigDecimal rate);
    boolean addReceiptType(ReceiptType type);
    boolean removeReceiptType(ReceiptType type);
    boolean setReceiptLimit(ReceiptType type, BigDecimal limit);
}