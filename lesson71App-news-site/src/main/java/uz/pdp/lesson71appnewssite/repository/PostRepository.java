package uz.pdp.lesson71appnewssite.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson71appnewssite.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
