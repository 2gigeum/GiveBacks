package com.example.givebacks.post.controller;


import com.example.givebacks.post.domain.PostForm;
import com.example.givebacks.post.domain.entity.Post;
import com.example.givebacks.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 글 작성 버튼 클릭시 호출
     */
    @PostMapping("/post/volunteer-register")
    public ResponseEntity<String> postWrite(@RequestBody PostForm postForm) {

        postService.postWrite(postForm);

        return ResponseEntity.ok("글 작성 완료!");
    }


    @GetMapping("/post/volunteer-search")
    public String postList(Model model){
        List<Post>  postList=postService.getPostList();
        model.addAttribute("postList",postList);

        return "post/volunteer-search";

    }


}
