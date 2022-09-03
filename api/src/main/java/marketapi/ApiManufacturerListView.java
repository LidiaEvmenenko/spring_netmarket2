package marketapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiManufacturerListView {
    @JsonProperty
    private List<ApiManufacturerView> manufacturerViews = new ArrayList<>();

    public void addItem(ApiManufacturerView api){
        manufacturerViews.add(api);
    }
}
