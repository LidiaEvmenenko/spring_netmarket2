package marketfront.mapper.cart;


import lombok.RequiredArgsConstructor;
import marketfront.entity.Cart;
import marketfront.model.CartDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapper {

    public CartDto mapToDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setProductTitle(cart.getProduct().getTitle());
        dto.setProductPrice(cart.getProduct().getPrice());
        dto.setAmount(cart.getAmount());
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        dto.setProductId(cart.getProduct().getId());
        return dto;
    }

//    public Cart mapToEntity(CartDto dto) {
//        Cart cart = new Cart();
//        cart.setUser(dto.getUserId());
//        cart.setProduct(dto.getProductId());
//        cart.setAmount(dto.getAmount());
//        return cart;
//    }
}
