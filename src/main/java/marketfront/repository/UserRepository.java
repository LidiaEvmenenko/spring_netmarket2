package marketfront.repository;


import marketfront.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(value = "update Users set balance = :balance where id = :id",
            nativeQuery = true)
    void updateUserBalance(@Param("balance") Double balance, @Param("id") Long id);
}
