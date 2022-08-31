package marketproducts.repository;

import marketproducts.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  //  Cart findByTitle(String title);

    List<Cart> findAllByUserId(Long userId);

  //  Page<Cart> findAll(int pageIndex, int pageSize);

    void deleteById(Long id);

   // @Modifying
    @Query(value = "select c from Carts c where c.user_id = :user_id and c.product_id = :product_id",
            nativeQuery = true)
    Optional<Cart> findByUseridAndProductid(@Param("user_id") Long user_id, @Param("product_id") Long product_id);

    @Modifying
    @Query(value = "insert into carts (user_id, product_id, amount) values(:userId, :productId, :amount)",
            nativeQuery = true)
    void insertCart(@Param("userId") Long userId, @Param("productId") Long productId, @Param("amount") Double amount);

    @Modifying
    @Query(value = "update Carts set amount = :amount where id = :id",
            nativeQuery = true)
    void updateAmount(@Param("amount") Double amount, @Param("id") Long id);

}
