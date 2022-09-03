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
public class ApiProductsListView {
    @JsonProperty
    List<ApiProductsView> productsViewList = new ArrayList<>();

    public void addProductToList(ApiProductsView view){
        productsViewList.add(view);
    }
}
