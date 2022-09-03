package marketapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiProductsView {
    @JsonProperty
    private Long id;
    @JsonProperty
    private int nom = 0;
    @JsonProperty
    private String title;
    @JsonProperty
    private String categoryTitle;
    @JsonProperty
    private Double price;
    @JsonProperty
    private byte[] image;
    @JsonProperty
    private Date expiration_date;
    @JsonProperty
    private String description;
    @JsonProperty
    private Double weight;
    @JsonProperty
    private String manufacturerTitle;
    @JsonProperty
    private Double count;

}
