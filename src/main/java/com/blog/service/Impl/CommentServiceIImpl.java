package com.blog.service.Impl;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceIImpl implements CommentService {
private PostRepository postRepository;
private CommentRepository commentRepository;

    public CommentServiceIImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post= postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post is not found with id: " +postId)
        );
        Comment comment = new Comment();

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);
     CommentDto dtos  = mapToDto(savedComment);
        return dtos;
    }

    @Override
    public void deleteCommentById(long id) {
        Comment comment=commentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("comment is not found with id: " +id)
        );
        commentRepository.deleteById(id);

    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentDtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public CommentDto updateCommentById(long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("commentId is not found by id: " + commentId)
        );
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        Comment updateComment = commentRepository.save(comment);
         CommentDto dto= mapToDto(updateComment);
        return dto;
    }

    CommentDto mapToDto(Comment comment){
        CommentDto dto= new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        return dto;
    }
}
