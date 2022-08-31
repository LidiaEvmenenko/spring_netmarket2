package marketfront.repository;


import marketfront.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByTitle(String title);

    @Modifying
    @Query(value = "insert into categories (title) values(:title)", nativeQuery = true)
    void insertCategory(@Param("title") String title);

}
