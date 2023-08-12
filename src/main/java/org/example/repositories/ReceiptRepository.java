package org.example.repositories;


import org.example.models.Receipt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReceiptRepository {

    private static final List<Receipt> receiptsList = new ArrayList<>();

    public static void addReceipt(Receipt receipt) {
        receiptsList.add(receipt);
    }

    public static List<Receipt> getAllReceipts() {
        return Collections.unmodifiableList(receiptsList);
    }
}