package marketapi;

import java.util.ArrayList;
import java.util.List;

public class ApiCategoryListView {
    private List<ApiCategoryView> categoryViews = new ArrayList<>();

    public List<ApiCategoryView> getCategoryViews() {
        return categoryViews;
    }

    public void setCategoryViews(List<ApiCategoryView> categoryViews) {
        this.categoryViews = categoryViews;
    }
    public void addItem(ApiCategoryView api){
        categoryViews.add(api);
    }
}
