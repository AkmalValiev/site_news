package uz.pdp.lesson71appnewssite.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lesson71appnewssite.entity.template.AbstractEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends AbstractEntity {

    @Column(nullable = false, columnDefinition = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

}
