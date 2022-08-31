package marketproducts.controller;

import lombok.RequiredArgsConstructor;
import marketapi.ApiCategoryListView;
import marketapi.ApiCategoryView;
import marketapi.ApiProductsListView;
import marketapi.ApiProductsView;
import marketproducts.entity.Category;
import marketproducts.entity.Product;
import marketproducts.mapper.product.ProductMapper;
import marketproducts.service.CategoryService;
import marketproducts.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @PostMapping()
    public void createNewCategory(@RequestBody ApiCategoryView category) {
        categoryService.create(category.getTitle());
    }

    @GetMapping
    public ApiCategoryListView findAllCategory(){
        ApiCategoryListView categoryListView = new ApiCategoryListView();
        List<Category> categories = categoryService.findAll();
        for (Category c:categories) {
            ApiCategoryView view = new ApiCategoryView();
            view.setTitle(c.getTitle());
            categoryListView.addItem(view);
        }
        return categoryListView;
    }


    @GetMapping("/page")
    public ApiProductsListView findByCategory(@RequestParam(name = "p", defaultValue = "1") int pageIndex,
                                              @RequestParam(name = "title", defaultValue = "1") String title) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        List<Product> products =  productService.findProductsByCategory(title);
        ApiProductsListView listView = new ApiProductsListView();
        for (Product p :products) {
            ApiProductsView view = productMapper.mapToView(p);
            listView.addProductToList(view);
        }
        return listView;
    }
}
