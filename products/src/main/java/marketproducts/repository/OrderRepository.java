package marketproducts.repository;

import marketproducts.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
   // Optional<User> findByTitle(Long id);
}
