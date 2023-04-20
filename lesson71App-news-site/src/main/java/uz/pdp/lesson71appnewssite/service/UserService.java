package uz.pdp.lesson71appnewssite.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson71appnewssite.entity.Role;
import uz.pdp.lesson71appnewssite.entity.User;
import uz.pdp.lesson71appnewssite.payload.ApiResponse;
import uz.pdp.lesson71appnewssite.payload.UserDto;
import uz.pdp.lesson71appnewssite.repository.RoleRepository;
import uz.pdp.lesson71appnewssite.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    public ApiResponse addUser(UserDto userDto) {
        boolean existsByUsername = userRepository.existsByUsername(userDto.getUsername());
        if (existsByUsername)
            return new ApiResponse("Bunday username mavjud!", false);

        Optional<Role> optionalRole = roleRepository.findById(userDto.getRoleId());
        if (!optionalRole.isPresent())
            return new ApiResponse("ROle topilmadi!", false);

        User user = new User(
                userDto.getFullName(),
                userDto.getUsername(),
                userDto.getPassword(),
                optionalRole.get(),
                true
        );

        userRepository.save(user);
        return new ApiResponse("User saqlandi!", true);
    }
}
