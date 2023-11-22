package com.example.givebacks.user.service;

import com.example.givebacks.user.domain.SignInForm;
import com.example.givebacks.user.domain.entity.User;
import com.example.givebacks.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignInService {

    private final UserRepository userRepository;


    /**
     * 유저 로그인
     */
    public boolean signIn(SignInForm signInForm) {
        Optional<User> userOptional = userRepository.findByUserEmailAndUserPassword(signInForm.getUserEmail(), signInForm.getUserPassword());
        return userOptional.isPresent();
    }


}
