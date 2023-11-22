package com.example.givebacks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    //페이지 홈
    @GetMapping("/")
    public String index() {
        return "index";
    }

    //회원가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "user/signup";
    }

    @GetMapping("/user/signin")
    public String login() {
        return "user/signin";
    }

}
