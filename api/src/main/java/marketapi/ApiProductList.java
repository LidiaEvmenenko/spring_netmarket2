package marketapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApiProductList {
    @JsonProperty
    List<ApiProductView> products;
}
