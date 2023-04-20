package uz.pdp.lesson71appnewssite.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @NotNull(message = "title bo'sh bo'lmasin!")
    private String title;

    @NotNull(message = "text bo'sh bo'lmasin!")
    private String text;

    @NotNull(message = "url bo'sh bo'lmasin!")
    private String url;

}
