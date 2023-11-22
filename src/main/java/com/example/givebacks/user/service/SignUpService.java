package com.example.givebacks.user.service;


import com.example.givebacks.user.domain.SignUpForm;
import com.example.givebacks.user.domain.entity.User;
import com.example.givebacks.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;


    /**
     * 이메일 중복 체크
     */
    public boolean emailCheck(String email) {
        boolean isEmail = userRepository.existsByUserEmail(email);
        return isEmail;
    }


    /**
     * 회원가입 버튼 클릭시 호출
     * 쿼리 작동중 예외 발생시 롤백을 위한 Transactional사용
     */
    @Transactional
    public void signUpSubmit(SignUpForm form) {
        User user = User.builder()
                .userEmail(form.getUserEmail())
                .userPassword(form.getUserPassword())
                .userName(form.getUserName())
                .userNickname(form.getUserNickname())
                .registerDate(LocalDateTime.now())
                .build();

        userRepository.save(user);

    }

}
