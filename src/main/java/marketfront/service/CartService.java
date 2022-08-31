package marketfront.service;


import lombok.RequiredArgsConstructor;
import marketfront.entity.Cart;
import marketfront.entity.User;
import marketfront.mapper.cart.CartMapper;
import marketfront.model.CartDto;
import marketfront.model.ProductDto;
import marketfront.repository.CartRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartService {
    private  final CartRepository cartRepository;
    private  final CartMapper cartMapper;
    private  final UserService userService;



    public Page<Cart> findAll(int pageIndex, int pageSize){
        return cartRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public void deleteById(Long id){
        cartRepository.deleteById(id);
    }


    @Transactional
    public void addNewProductToCart(ProductDto productDto, Double amount){
        Optional<Cart> cartFind = cartRepository.findByUseridAndProductid(1L, productDto.getId());
        if (cartFind.isEmpty()){
          //  Cart cart = new Cart();
            cartRepository.insertCart(1L, productDto.getId(), amount);
        }else{
            cartFind.get().setAmount(amount);
        }


    }
    public Double sumItogCart(Long id){
        List<Cart> carts = cartRepository.findAllByUserId(1L);
        Double sum = Double.valueOf(0);
        if (!carts.isEmpty()) {
        for (int i=0; i<carts.size(); i++) {
            CartDto cartDto = cartMapper.mapToDto(carts.get(i));
            sum = sum + cartDto.getProductPrice() * cartDto.getAmount();
        }
        }
        return sum;
    }
    public Double userBalance(Long id){
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) { return Double.valueOf(0);}
        return user.get().getBalance();
    }

    public void userBuy(Long id, Double itog){
        userService.updateUserBalance(id, itog);
    }

    @Transactional
    public void incAmount(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if (!cart.isEmpty()){
            cartRepository.updateAmount(cart.get().getAmount() + 1, cart.get().getId());
        }
    }

    @Transactional
    public void subAmount(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if (!cart.isEmpty()){
            if (cart.get().getAmount() > 0) {
                System.out.println("cart.get().getId() = "+cart.get().getId());
                System.out.println("cart.get().getAmount() = "+cart.get().getAmount());
                cartRepository.updateAmount(cart.get().getAmount() - 1, cart.get().getId());
            }
        }
    }
}
