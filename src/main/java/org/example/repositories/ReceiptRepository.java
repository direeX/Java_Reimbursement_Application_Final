package org.example.repositories;


import org.example.models.Receipt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReceiptRepository {
    private List<String> receipts = new ArrayList<>();

    public void addReceipt(String formData) {
        receipts.add(formData);
    }

    public List<String> getAllReceipts() {
        return receipts;
    }
}