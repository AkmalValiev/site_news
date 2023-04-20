package uz.pdp.lesson71appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.lesson71appnewssite.entity.Post;
import uz.pdp.lesson71appnewssite.entity.User;
import uz.pdp.lesson71appnewssite.payload.ApiResponse;
import uz.pdp.lesson71appnewssite.payload.PostDto;
import uz.pdp.lesson71appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public ApiResponse addPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post qo'shildi!", true);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPost(Long id) {
        if (id == null)
            return new Post();

        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent())
            return new Post();
        return optionalPost.get();

    }

    public ApiResponse editPost(Long id, PostDto postDto) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent())
            return new ApiResponse("Post topilmadi!", false);

            Post post = optionalPost.get();
            post.setTitle(postDto.getTitle());
            post.setText(postDto.getText());
            post.setUrl(postDto.getUrl());
            postRepository.save(post);
            return new ApiResponse("Post taxrirlandi!", true);
    }

    public ApiResponse deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent())
            return new ApiResponse("Post topilmadi!", false);
        try {
            postRepository.deleteById(id);
            return new ApiResponse("Post o'chirildi!", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!", false);
        }
    }
}
