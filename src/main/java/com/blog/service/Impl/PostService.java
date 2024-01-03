package com.blog.service.Impl;

import com.blog.payload.PostDto;

import java.util.List;

public interface PostService {


    PostDto createPost(PostDto postDto);

    void deletePost(long id);


    List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortOrder);

    PostDto updatePost(long postId, PostDto postDto);
}
