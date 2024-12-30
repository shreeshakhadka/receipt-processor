package receipt.processor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ProcessReceiptRequest {
    @NotBlank(message = "Retailer name cannot be blank.")
    @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Retailer name contains invalid characters.")
    private String retailer;

    @NotBlank(message = "Purchase date cannot be blank.")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Purchase date must follow the format YYYY-MM-DD.")
    private String purchaseDate;

    @NotBlank(message = "Purchase time cannot be blank.")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Purchase time must follow the format HH:mm.")
    private String purchaseTime;

    @NotNull(message = "Items cannot be null.")
    @Size(min = 1, message = "At least one item is required.")
    @Valid
    private List<ItemDTO> items;

    @NotBlank(message = "Total cannot be blank.")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Total must be a valid decimal with two digits.")
    private String total;

    public String getRetailer() {
        return retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public String getTotal() {
        return total;
    }
}
