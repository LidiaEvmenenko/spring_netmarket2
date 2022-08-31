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
    public ApiProductsListView findAll(@RequestParam(name = "p") int pageIndex) {
        Page<Product> products = productService.findAll(pageIndex-1,5);
        ApiProductsListView listView = new ApiProductsListView();
        for (Product p :products) {
            ApiProductsView view = productMapper.mapToView(p);
            listView.addProductToList(view);
        }
        return listView;
    }

    @PostMapping
    public void createNewProduct(@RequestBody ApiProductsView product) {

        productService.create(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/manufacturer")
    public List<ProductDto> findByManufacturer(@RequestParam(name = "title") String title) {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productService.findByManufacturer(title);
        for (Product p:products) {
            ProductDto productDto = new ProductDto(p);
            productDtos.add(productDto);
        }
        return productDtos;
    }

}
