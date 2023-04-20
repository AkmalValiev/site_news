package uz.pdp.lesson71appnewssite.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotNull(message = "fullname bo'sh bo'lmasin!")
    private String fullName;

    @NotNull(message = "username bo'sh bo'lmasin!")
    private String username;

    @NotNull(message = "password bo'sh bo'lmasin!")
    private String password;

    @NotNull(message = "prePassword bo'sh bo'lmasin!")
    private String prePassword;

}
