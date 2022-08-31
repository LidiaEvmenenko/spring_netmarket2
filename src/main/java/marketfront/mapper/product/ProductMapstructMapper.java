package marketfront.mapper.product;


import marketfront.entity.Product;
import marketfront.model.ProductDto;
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

}














