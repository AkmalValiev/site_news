package uz.pdp.lesson71appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.lesson71appnewssite.entity.Comment;
import uz.pdp.lesson71appnewssite.entity.Post;
import uz.pdp.lesson71appnewssite.entity.User;
import uz.pdp.lesson71appnewssite.entity.enums.Permission;
import uz.pdp.lesson71appnewssite.payload.ApiResponse;
import uz.pdp.lesson71appnewssite.payload.CommentDto;
import uz.pdp.lesson71appnewssite.repository.CommentRepository;
import uz.pdp.lesson71appnewssite.repository.PostRepository;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    public Comment getComment(Long id) {
        if (id == null)
            return new Comment();

        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new Comment();
        return optionalComment.get();
    }


    public ApiResponse addComment(CommentDto commentDto) {

        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent())
            return new ApiResponse("Post topilmadi!", false);

        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setPost(optionalPost.get());
        commentRepository.save(comment);
        return new ApiResponse("Comment qo'shildi!", true);
    }

    public ApiResponse editComment(Long id, String text) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("Comment topilmadi!", false);

        Comment comment = optionalComment.get();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (comment.getCreatedBy().getId()==user.getId()){
            comment.setText(text);
            commentRepository.save(comment);
            return new ApiResponse("Comment taxrirlandi!", true);
        }
        return new ApiResponse("Siz faqat o'zingizning commentingizni o'zgartira olasiz!", false);
    }


    public ApiResponse deleteComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("Comment topilmadi!", false);

        Comment comment = optionalComment.get();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean authorityYes = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(Permission.DELETE_COMMENT)){
                authorityYes = true;
                break;
            }
        }

        if (comment.getCreatedBy().getId()== user.getId() || authorityYes){
            try {
                commentRepository.deleteById(id);
                return new ApiResponse("Comment o'chirildi!", true);
            }catch (Exception e){
                return new ApiResponse("Xatolik!", false);
            }
        }
        return new ApiResponse("Bu commentni o'chirishga sizda ruxsat yo'q!", false);
    }
}
