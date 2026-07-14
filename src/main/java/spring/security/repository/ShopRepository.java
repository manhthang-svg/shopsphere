package spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.security.entity.Shop;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop,Long> {
}
