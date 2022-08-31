package marketproducts.mapper.product;

import lombok.RequiredArgsConstructor;
import marketapi.ApiProductsView;
import marketproducts.entity.Product;
import marketproducts.model.ProductDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

  //  private final CategoryService categoryService;

    public ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            dto.setCategoryTitle(product.getCategory().getTitle());
        }
        return dto;
    }
    public ApiProductsView mapToView(Product product) {
        ApiProductsView view = new ApiProductsView();
        view.setId(product.getId());
        view.setTitle(product.getTitle());
        view.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            view.setCategoryTitle(product.getCategory().getTitle());
        }
        return view;
    }

    public Product mapToEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());

        return product;
    }

}