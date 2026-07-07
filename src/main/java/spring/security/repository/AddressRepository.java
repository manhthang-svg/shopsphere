package spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.security.entity.Address;
import spring.security.entity.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    List<Address> findByUserId(Long id);

    Long countAddressByUser(Users user);
    @Modifying
    @Query("""
       UPDATE Address a
       SET a.isDefault = false
       WHERE a.user = :user 
""")
    int clearDefaultAddress(@Param("user") Users user);

    Optional<Address> findAddressByUserAndId(Users user, Long id);
}
