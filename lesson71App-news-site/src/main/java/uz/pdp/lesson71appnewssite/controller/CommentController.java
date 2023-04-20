package uz.pdp.lesson71appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson71appnewssite.entity.Comment;
import uz.pdp.lesson71appnewssite.payload.ApiResponse;
import uz.pdp.lesson71appnewssite.payload.CommentDto;
import uz.pdp.lesson71appnewssite.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/{id}")
    public HttpEntity<?> getComment(@PathVariable Long id){
        Comment comment = commentService.getComment(id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/addComment")
    public HttpEntity<?> addComment(@RequestBody CommentDto commentDto){
        ApiResponse apiResponse = commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editComment(@PathVariable Long id, @RequestBody String text){
       ApiResponse apiResponse = commentService.editComment(id, text);
       return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id){
        ApiResponse apiResponse = commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

}
