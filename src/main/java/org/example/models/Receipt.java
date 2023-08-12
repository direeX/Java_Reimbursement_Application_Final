package org.example.models;

import java.math.BigDecimal;

public class Receipt {
    private ReceiptType type;
    private BigDecimal amount;

    public Receipt(ReceiptType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    public ReceiptType getType() {
        return type;
    }

    public void setType(ReceiptType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "type=" + type +
                ", amount=" + amount +
                '}';
    }
}