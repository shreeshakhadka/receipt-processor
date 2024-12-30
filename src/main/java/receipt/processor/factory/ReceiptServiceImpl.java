package receipt.processor.factory;

import org.springframework.stereotype.Service;
import receipt.processor.common.PointsCalculator;
import receipt.processor.exceptions.BadRequestException;
import receipt.processor.exceptions.ReceiptNotFoundException;
import receipt.processor.model.Receipt;
import receipt.processor.service.ReceiptService;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final Map<String, Receipt> receiptStore = new ConcurrentHashMap<>();

    @Override
    public String processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receipt.setId(id);
        receiptStore.put(id, receipt);
        return id;
    }

    @Override
    public Integer getPoints(String id) {
        if (id == null || id.isEmpty() || !id.matches("^\\S+$")) {
            throw new BadRequestException("The receipt id is invalid.");
        }
        Receipt receipt = receiptStore.get(id);
        if (receipt == null) {
            throw new ReceiptNotFoundException("No receipt found for ID: " + id);
        }
        return PointsCalculator.calculatePoints(receipt);
    }
}
