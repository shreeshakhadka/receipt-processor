package receipt.processor.common;

import receipt.processor.dto.ItemDTO;
import receipt.processor.dto.ProcessReceiptRequest;
import receipt.processor.model.Item;
import receipt.processor.model.Receipt;

import java.util.stream.Collectors;

public class ReceiptMapper {
    public static Receipt toEntity(ProcessReceiptRequest request) {
        Receipt receipt = new Receipt();
        receipt.setRetailer(request.getRetailer());
        receipt.setPurchaseDate(request.getPurchaseDate());
        receipt.setPurchaseTime(request.getPurchaseTime());
        receipt.setTotal(request.getTotal());
        receipt.setItems(request.getItems().stream()
                .map(ReceiptMapper::toEntity)
                .collect(Collectors.toList()));
        return receipt;
    }

    private static Item toEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setShortDescription(itemDTO.getShortDescription());
        item.setPrice(itemDTO.getPrice());
        return item;
    }
}