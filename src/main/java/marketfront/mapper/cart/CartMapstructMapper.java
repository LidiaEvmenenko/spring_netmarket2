package marketfront.mapper.cart;

import marketfront.entity.Cart;
import marketfront.entity.Product;
import marketfront.entity.User;
import marketfront.model.CartDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartMapstructMapper {
    @Mappings(value = {
            @Mapping(target = "id", source = "cart.id"),
            @Mapping(target = "productTitle", source = "product.title"),
            @Mapping(target = "productPrice", source = "product.price"),
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "userId", source = "user.id")
    })
    CartDto mapToDto(Product product, User user, Cart cart);
}
