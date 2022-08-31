package marketapi;

import java.util.ArrayList;
import java.util.List;

public class ApiManufacturerListView {
    private List<ApiManufacturerView> manufacturerViews = new ArrayList<>();

    public List<ApiManufacturerView> getManufacturerViews() {
        return manufacturerViews;
    }

    public void setManufacturerViews(List<ApiManufacturerView> manufacturerViews) {
        this.manufacturerViews = manufacturerViews;
    }
    public void addItem(ApiManufacturerView api){
        manufacturerViews.add(api);
    }
}
