package com.example.givebacks.post.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostForm {

    String postTitle;
    String postContent;

}
