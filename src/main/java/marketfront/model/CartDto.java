package marketfront.model;


import lombok.Data;
import marketfront.entity.Cart;

@Data
public class CartDto {
    private Long id;
    private Long userId;
    private Long productId;
    private String productTitle;
    private Double productPrice;
    private Double amount;

  public CartDto(Cart cart) {
    this.id = cart.getId();
    this.userId = cart.getUser().getId();
    this.productId = cart.getProduct().getId();
    this.productTitle = cart.getProduct().getTitle();
    this.productPrice = cart.getProduct().getPrice();
    this.amount = cart.getAmount();
  }

  public CartDto() {
  }
}
