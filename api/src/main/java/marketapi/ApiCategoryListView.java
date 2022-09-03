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
public class ApiCategoryListView {
    @JsonProperty
    private List<ApiCategoryView> categoryViews = new ArrayList<>();

    public void addItem(ApiCategoryView api){
        categoryViews.add(api);
    }
}
