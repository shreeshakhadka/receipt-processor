package receipt.processor.service;


import receipt.processor.model.Receipt;

public interface ReceiptService {
    String processReceipt(Receipt receipt);

    Integer getPoints(String id);
}
