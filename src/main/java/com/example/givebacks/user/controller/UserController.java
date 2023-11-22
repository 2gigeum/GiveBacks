package com.example.givebacks.user.controller;


import com.example.givebacks.user.domain.SignInForm;
import com.example.givebacks.user.domain.SignUpForm;
import com.example.givebacks.user.service.SendEmailService;
import com.example.givebacks.user.service.SignInService;
import com.example.givebacks.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final SignUpService signUpService;
    private final SignInService signInService;
    private final SendEmailService sendEmailService;


    /**
     * 유저 로그아웃
     * 세선 무효화
     */
    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        // 세션 무효화
        session.invalidate();
        return "redirect:/"; // 로그아웃 후 메인 페이지로 리다이렉트
    }

    /**
     * 유저 로그인
     * 유저의 세션을 프론트로 전달
     */
    @PostMapping("/user/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInForm signInForm, HttpSession session) {
        boolean isLogin = signInService.signIn(signInForm);

        if (isLogin) {
            session.setAttribute("loginUserEmail", signInForm.getUserEmail());
            //세션 유효시간 30분
            session.setMaxInactiveInterval(1800);
            return ResponseEntity.ok().body("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }


    /**
     * 이메일 중복 체크
     */
    @GetMapping("/user/email/check")
    public ResponseEntity<?> emailCheck(@RequestParam String email) {
        boolean isEmail = signUpService.emailCheck(email);

        if (isEmail) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 이메일입니다.");
        } else {
            return ResponseEntity.ok("사용가능한 이메일입니다.");
        }

    }

    /**
     * 회원 가입
     */
    @PostMapping("/user/signup")
    public String signUpSubmit(
            @RequestBody SignUpForm form
    ) {
        // 넘어온 유저 정보를 DB저장 회원가입.
        signUpService.signUpSubmit(form);
        return "redirect:/user/login";
    }


    /**
     * 이메일 유효 토큰 검사
     */
    @PostMapping("/user/verification-code")
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
