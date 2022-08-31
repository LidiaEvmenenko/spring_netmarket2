package marketfront.controller;



import lombok.RequiredArgsConstructor;
import marketfront.entity.Product;
import marketfront.mapper.product.ProductMapper;
import marketfront.model.ProductDto;
import marketfront.service.CategoryService;
import marketfront.service.ProductService;
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


//    @GetMapping
//    public List<ProductDto> getProducts() {
//
//        return productService.getProducts().stream()
//                .map(productMapper::mapToDto)
//               // .toList();
//                .collect(toList());
//    }
    //@BenchMark
    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 5).map(ProductDto::new);
        //   return productService.findAll(pageIndex, 10);
    }

    @PostMapping
    public void createNewProduct(@RequestBody ProductDto product) {
        //   return productMapper.mapToDto(productService.create(product));
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
