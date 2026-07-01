package spring.security.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.security.entity.Users;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    @Query("select u from Users u where u.username = :username")
    Optional<Users> findByUsernameWithRolesAndPermissions(@Param("username") String username);

    boolean  existsByUsername(String username);
    Optional<Users> findById(Long id);

}
