package com.blog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;



@Data
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min = 5 ,message = "title should be at least 5 characters")
    private String title;
    @NotEmpty
    @Size(min = 5 ,message = "description should be at least 5 characters")
    private String description;
    @NotEmpty
    @Size(min = 5 ,message = "content should be at least 5 characters")
    private String content;
  private String message;

}
