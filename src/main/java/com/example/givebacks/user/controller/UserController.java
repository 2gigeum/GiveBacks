package com.example.givebacks.user.controller;


import com.example.givebacks.user.domain.SignUpForm;
import com.example.givebacks.user.service.SendEmailService;
import com.example.givebacks.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;
    private final SendEmailService sendEmailService;



    @PostMapping("/user/signup")
    public String signUpSubmit(
            SignUpForm form
    ) {
        signUpService.signUpSubmit(form);
        return "redirect:/";
    }


    @PostMapping("/user/verificationCode")
    public ResponseEntity<String> verificationCode(@RequestBody Map<String, String> code) {
        String token = code.get("code");
        if (sendEmailService.isValidCode(token)) {
            // 인증 성공
            return ResponseEntity.ok("인증 성공");
        } else {
            // 인증 실패
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");
        }

    }


}
