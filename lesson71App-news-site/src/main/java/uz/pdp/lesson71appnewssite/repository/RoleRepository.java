package uz.pdp.lesson71appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson71appnewssite.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);

}
