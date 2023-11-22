package com.example.givebacks.user.controller;


import com.example.givebacks.user.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SendMailController {
    private final SendEmailService sendEmailService;


    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(String email) {
        sendEmailService.sendEmail(email);
        return ResponseEntity.ok("이메일 전송 성공");
    }



}
