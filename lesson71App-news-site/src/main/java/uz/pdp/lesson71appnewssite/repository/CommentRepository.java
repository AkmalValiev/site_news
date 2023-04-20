package uz.pdp.lesson71appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson71appnewssite.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
