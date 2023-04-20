package uz.pdp.lesson71appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson71appnewssite.entity.Role;
import uz.pdp.lesson71appnewssite.payload.ApiResponse;
import uz.pdp.lesson71appnewssite.payload.RoleDto;
import uz.pdp.lesson71appnewssite.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    public ApiResponse addRole(RoleDto roleDto) {

        if (roleRepository.existsByName(roleDto.getName()))
            return new ApiResponse("Bunday role bor!", false);

        Role role = new Role(
                roleDto.getName(),
                roleDto.getPermissions(),
                roleDto.getDescription()
        );

        roleRepository.save(role);

        return new ApiResponse("Role Saqlandi!", true);
    }
}
