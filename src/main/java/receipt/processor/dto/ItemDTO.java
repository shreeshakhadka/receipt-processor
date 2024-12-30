package receipt.processor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ItemDTO {
    @NotBlank(message = "Short description cannot be blank.")
    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Short description contains invalid characters.")
    private String shortDescription;

    @NotBlank(message = "Price cannot be blank.")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Price must be a valid decimal with two digits.")
    private String price;

    public ItemDTO() {
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getPrice() {
        return price;
    }

}
