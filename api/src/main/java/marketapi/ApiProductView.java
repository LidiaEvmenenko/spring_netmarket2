package marketapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiProductView {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String title;
    @JsonProperty
    private BigDecimal price;
    @JsonProperty
    private String categoryTitle;
}
