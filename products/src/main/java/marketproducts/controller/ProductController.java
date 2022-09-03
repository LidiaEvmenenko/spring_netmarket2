package marketproducts.controller;



import lombok.RequiredArgsConstructor;
import marketapi.ApiProductsListView;
import marketapi.ApiProductsView;
import marketproducts.entity.Product;
import marketproducts.mapper.product.ProductMapper;
import marketproducts.model.ProductDto;
import marketproducts.service.CategoryService;
import marketproducts.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    @GetMapping
    public ApiProductsListView findAll() {
        return productService.findAll();
    }

    @PostMapping
    public void createNewProduct(@RequestBody ApiProductsView product) {
        productService.create(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/by_category")
    public ApiProductsListView findByCategory(@RequestParam(name = "title", defaultValue = "Все продукты") String title) {
        return productService.findProductsByCategory(title);
    }

}
