package uz.pdp.lesson71appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson71appnewssite.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}
