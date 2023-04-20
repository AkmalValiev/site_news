package uz.pdp.lesson71appnewssite.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.lesson71appnewssite.entity.Role;
import uz.pdp.lesson71appnewssite.entity.User;
import uz.pdp.lesson71appnewssite.entity.enums.Permission;
import uz.pdp.lesson71appnewssite.repository.RoleRepository;
import uz.pdp.lesson71appnewssite.repository.UserRepository;
import uz.pdp.lesson71appnewssite.utils.AppConstants;

import java.util.Arrays;

import static uz.pdp.lesson71appnewssite.entity.enums.Permission.*;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initialMode;


    @Override
    public void run(String... args) {
        if (initialMode.equals("always")){
            Permission[] values = Permission.values();

            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(values),
                    "Sistama admini!"
            ));

            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_COMMENT),
                    "Oddiy foydalanuvchi!"
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));

            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));
        }

    }
}
