package marketproducts.repository;

import marketproducts.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Optional<Manufacturer> findByTitle(String title);

    @Modifying
    @Query(value = "insert into manufacturers (title, balance) values(:title, :balance)",
            nativeQuery = true)
    void insertManufacturer(@Param("title") String title, @Param("balance") Double balance);
}
