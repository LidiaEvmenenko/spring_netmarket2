package marketapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCartItem {
    @JsonProperty
    private Long Id;
    @JsonProperty
    private Long productId;
    @JsonProperty
    private String productTitle;
    @JsonProperty
    private int quantity;
    @JsonProperty
    private BigDecimal pricePerProduct;
    @JsonProperty
    private BigDecimal price;
}
