package marketfront.controller;


import lombok.RequiredArgsConstructor;
import marketfront.entity.Product;
import marketfront.exceptions.ResourceNotFoundException;
import marketfront.mapper.product.ProductMapper;
import marketfront.model.CartDto;
import marketfront.service.CartService;
import marketfront.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final ProductMapper productMapper;



    @PostMapping("/{id},{count}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewProductToCart(@PathVariable Long id, @PathVariable Double count) {
        Product product = productService.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product id = "+ id +" not found"));
        cartService.addNewProductToCart(productMapper.mapToDto(product), count);
    }

    @GetMapping
    public Page<CartDto> findAllToCart(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
     //   return cartService.findAll(pageIndex - 1, 5);
        return cartService.findAll(pageIndex - 1, 5).map(CartDto::new);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteByIdCart(@PathVariable Long id){
        cartService.deleteById(id);
    }

    @GetMapping("/sum")
    public Double sumCart(){
        return cartService.sumItogCart(1L);
    }

    @GetMapping("/inc/{id}")
    public void incCartAmount(@PathVariable Long id){
        cartService.incAmount(id);
    }

    @GetMapping("/sub/{id}")
    public void subCartAmount(@PathVariable Long id){
        cartService.subAmount(id);
    }

    @GetMapping("/balance/{id}")
    public Double userBalance(@PathVariable Long id){
        return cartService.userBalance(id);
    }

    @GetMapping("/buy/{id},{itog}")
    public void userBuy(@PathVariable Long id, @PathVariable Double itog){
        cartService.userBuy(id, itog);
    }

}
