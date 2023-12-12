package com.example.givebacks.post.service;

import com.example.givebacks.post.domain.PostForm;
import com.example.givebacks.post.domain.entity.Post;
import com.example.givebacks.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    /*
     글 작성
     */
    public void postWrite(PostForm postForm) {
        String title = postForm.getPostTitle();
        String content = postForm.getPostContent();

        Post post = Post.builder()
                .title(title)
                .content(content)
                .registerDt(LocalDateTime.now())
                .build();
        postRepository.save(post);

    }

    /*
        게시글 리스트 조회
     */
    public List<Post> getPostList(){
        return postRepository.findAll();
    }




}
