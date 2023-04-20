package uz.pdp.lesson71appnewssite.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @NotNull(message = "text bo'sh bo'lmasligi kerak")
    private String text;

    @NotNull(message = "postId bo'sh bo'lmasligi kerak")
    private Long postId;

}
