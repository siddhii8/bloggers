package com.blog.payload;

import com.blog.entity.Post;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data

public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;

}
