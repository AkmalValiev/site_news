package uz.pdp.lesson71appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.lesson71appnewssite.entity.User;
import uz.pdp.lesson71appnewssite.exceptions.ResourceNotFoundException;
import uz.pdp.lesson71appnewssite.payload.ApiResponse;
import uz.pdp.lesson71appnewssite.payload.RegisterDto;
import uz.pdp.lesson71appnewssite.repository.RoleRepository;
import uz.pdp.lesson71appnewssite.repository.UserRepository;
import uz.pdp.lesson71appnewssite.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
            return new ApiResponse("Parollar maos kelmadi!", false);

        if (userRepository.existsByUsername(registerDto.getUsername()))
            return new ApiResponse("Bunday username li user mavjud!", false);

        User user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(()-> new ResourceNotFoundException("role", "name", AppConstants.USER)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz!", true);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User topilmadi!"));
    }
}
