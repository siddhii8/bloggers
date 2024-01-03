package com.blog.service;

import com.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    void deleteCommentById(long id);

    CommentDto updateCommentById(long commentId, CommentDto commentDto);

    List<CommentDto> getAllComments();

   // CommentDto updateCommentById(long commentId, CommentDto commentDto);

   // CommentDto createComment(long commentId, CommentDto commentDto);
}
