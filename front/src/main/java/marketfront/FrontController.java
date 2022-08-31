package marketfront;

import marketapi.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1/")
public class FrontController {
    private final RestTemplate restTemplate;
    private final String url;

    public FrontController(RestTemplate restTemplate,
                           @Value("http://localhost:8190/app-service/api/v1/") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @GetMapping("category")
    public ApiCategoryListView findAllCategory(){
        ApiCategoryListView categoryList = restTemplate.getForEntity(url + "category",  ApiCategoryListView.class).getBody();
        return categoryList;
    }

    @GetMapping("manufacturer")
    public ApiManufacturerListView findAllManufacturer(){
        ApiManufacturerListView manufacturerList = restTemplate.getForEntity(url + "manufacturer",  ApiManufacturerListView.class).getBody();
        return manufacturerList;
    }

    @PostMapping("category")
    public void createNewCategory(@RequestBody ApiCategoryView body) {
        ApiCategoryView view = new ApiCategoryView();
        view.setTitle(body.getTitle());
        restTemplate.postForObject(url + "category", view, ApiCategoryView.class);
    }

    @PostMapping("manufacturer")
    public void createNewManufacturer(@RequestBody ApiManufacturerView body) {
        ApiManufacturerView view = new ApiManufacturerView();
        view.setTitle(body.getTitle());
        restTemplate.postForObject(url + "manufacturer", view, ApiManufacturerView.class);
    }

    @GetMapping("products")
    public ApiProductsListView findAllProducts(@RequestParam(name = "p", defaultValue = "1") int pageIndex){
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        ApiProductsListView productList = restTemplate.getForEntity(url + "products?p="+pageIndex,  ApiProductsListView.class).getBody();
        return productList;
    }

    @DeleteMapping("products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        restTemplate.delete(url + "products/" + id, ApiProductsView.class);
    }

    @PostMapping("products")
    public void createNewProduct(@RequestBody ApiProductsView product) {
        restTemplate.postForObject(url + "products", product, ApiProductsView.class);
    }
}
