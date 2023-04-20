package uz.pdp.lesson71appnewssite.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lesson71appnewssite.entity.enums.Permission;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    @NotBlank
    private String name;
    private String description;
    @NotEmpty
    private List<Permission> permissions;

}
