package spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.security.entity.Shop;
import spring.security.entity.Users;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop,Long> {
    boolean existsShopByOwner(Users owner);

    Shop findByOwner(Users owner);
}
