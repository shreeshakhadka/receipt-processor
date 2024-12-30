package receipt.processor.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import receipt.processor.common.ReceiptMapper;
import receipt.processor.dto.GetPointsResponse;
import receipt.processor.dto.ProcessReceiptRequest;
import receipt.processor.dto.ProcessReceiptResponse;
import receipt.processor.model.Receipt;
import receipt.processor.service.ReceiptService;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<ProcessReceiptResponse> processReceipt(@Valid @RequestBody ProcessReceiptRequest request) {
        Receipt receipt = ReceiptMapper.toEntity(request);
        String id = receiptService.processReceipt(receipt);
        return ResponseEntity.ok(new ProcessReceiptResponse(id));
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<GetPointsResponse> getPoints(@PathVariable String id) {
        Integer points = receiptService.getPoints(id);
        return ResponseEntity.ok(new GetPointsResponse(points));
    }
}
