package com.example.givebacks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/user/signup")
    public String signup(){
        return "user/signup";
    }

}
