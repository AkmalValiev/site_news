package uz.pdp.lesson71appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson71appnewssite.entity.Post;
import uz.pdp.lesson71appnewssite.payload.ApiResponse;
import uz.pdp.lesson71appnewssite.payload.PostDto;
import uz.pdp.lesson71appnewssite.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;

    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping("/add")
    public HttpEntity<?> addPost(@RequestBody PostDto postDto){
        ApiResponse apiResponse = postService.addPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getPosts(){
        List<Post> posts = postService.getPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getPOst(@PathVariable Long id){
        Post post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @PutMapping("/{id}")
    public HttpEntity<?> editPost(@PathVariable Long id, @RequestBody PostDto postDto){
        ApiResponse apiResponse = postService.editPost(id, postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePost(@PathVariable Long id){
        ApiResponse apiResponse = postService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

}
