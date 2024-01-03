package com.blog.service.Impl;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {

        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        Post saved = postRepo.save(post);


        PostDto dto = new PostDto();
        dto.setId( saved.getId());
        dto.setContent( saved.getContent());
        dto.setDescription( saved.getDescription());
        dto.setTitle( saved.getTitle());
        dto.setMessage("Post is created");
        return dto;

    }

    @Override
    public void deletePost(long id) {
        Post post   =postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("post is not found with id "+id)
        );

    }

    @Override
    public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortOrder) {
Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable  = PageRequest.of(pageNo, pageSize,  sort);
        Page<Post> pagePost = postRepo.findAll(pageable);
        List<Post>posts= pagePost.getContent();
        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
     Post post =  postRepo.findById(postId).orElseThrow(
                () ->new ResourceNotFoundException("post not found with id: "+postId)
        );
     post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
     post.setContent(postDto.getContent());
     Post savedPost = postRepo.save(post);
        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    PostDto mapToDto(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        dto.setTitle(post.getTitle());
        return dto;
    }
}