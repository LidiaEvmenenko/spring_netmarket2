package marketproducts.mapper.product;

import marketapi.ApiProductsView;
import marketproducts.entity.Product;
import marketproducts.model.ProductDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapstructMapper {
    @Mappings(value = {
            @Mapping(target = "id", source = "product.id"),
            @Mapping(target = "categoryTitle", source = "category.title"),
            @Mapping(target = "manufacturerTitle", source = "manufacturer.title")
    })
    ProductDto mapToDto(Product product);

    @Mappings(value = {
            @Mapping(target = "id", source = "product.id"),
            @Mapping(target = "categoryTitle", source = "category.title"),
            @Mapping(target = "manufacturerTitle", source = "manufacturer.title")
    })
    ApiProductsView mapToView(Product product);

}














