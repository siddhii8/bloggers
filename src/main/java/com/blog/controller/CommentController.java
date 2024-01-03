package com.blog.controller;

import com.blog.payload.CommentDto;
import com.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    //create comment
   // http:localhost:8080/api/comments?postId=1
    @PostMapping
    public ResponseEntity<CommentDto> createComment(
            @RequestParam("postId") long postId,
                    @RequestBody CommentDto commentDto){
      CommentDto dtos  = commentService.createComment(postId, commentDto);
       return new ResponseEntity<>(dtos, HttpStatus.CREATED);
    }
    //http:localhost:8080/api/comments/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable ("id") long id){
        commentService.deleteCommentById(id);
        return new ResponseEntity<>("comment is deleted", HttpStatus.OK);

    }
    //http://localhost:8080/api/comments?commentId=4
//    @PutMapping
//    public ResponseEntity<CommentDto> updateCommentById(
//            @RequestParam ("commentId") long commentId,
//             @RequestBody CommentDto commentDto  ){
//        CommentDto dtos = commentService.updateCommentById(commentId, commentDto);
//        return new ResponseEntity<>(dtos, HttpStatus.OK);
   // }
    //http://localhost:8080/api/comments
    @GetMapping
    public ResponseEntity<List<CommentDto>>getAllComments(){
        List<CommentDto> commentsDtos = commentService.getAllComments();
return new ResponseEntity<>(commentsDtos, HttpStatus.OK);
    }
    //http://localhost:8080/api/comments?commentId=4
    @PutMapping
    public ResponseEntity<CommentDto>updateCommentById(
            @RequestParam("commentId") long commentId,
            @RequestBody CommentDto commentDto) {
        CommentDto dtos = commentService.updateCommentById(commentId, commentDto);
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }




}
