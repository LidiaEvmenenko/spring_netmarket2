package marketapi;

import java.util.ArrayList;
import java.util.List;

public class ApiProductsListView {
    List<ApiProductsView> productsViewList = new ArrayList<>();

    public List<ApiProductsView> getProductsViewList() {
        return productsViewList;
    }

    public void setProductsViewList(List<ApiProductsView> productsViewList) {
        this.productsViewList = productsViewList;
    }

    public void addProductToList(ApiProductsView view){
        productsViewList.add(view);
    }
}
