package uz.pdp.lesson71appnewssite.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull(message = "username bo'sh bo'lmasin!")
    private String username;

    @NotNull(message = "password bo'sh bo'lmasin!")
    private String password;

}
