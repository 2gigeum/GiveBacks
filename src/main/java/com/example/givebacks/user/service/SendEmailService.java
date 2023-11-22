package com.example.givebacks.user.service;

import com.example.givebacks.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendEmailService {

    private final JavaMailSender javaMailSender;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;



    /**
     * 이메일 토큰 redis 저장
     */
    public void emailTokenSave(String token) {
        SetOperations<String, String> valueOperations = redisTemplate.opsForSet();
        String key = "emailToken";
        valueOperations.add(key, token);
        redisTemplate.expire(key, 60, TimeUnit.SECONDS);

        // 1분뒤 토큰 유효시간 만료 (토큰 데이터 삭제)

    }

    /**
     * 인증 코드 생성
     */
    public String createEmailAuthCode() {
        Random randomCode = new Random();
        RandomString code = new RandomString(6, randomCode);

        return code.nextString();
    }


    /**
     * 인증코드 검증
     * 클라이언트측에서 이메일 검증 코드 확인 클릭시 호출
     */
    public boolean isValidCode(String token) {
        // 데이터베이스 또는 저장된 코드와 비교하는 로직을 작성
        SetOperations<String, String> valueOperations = redisTemplate.opsForSet();
        Set<String> tokens = valueOperations.members("emailToken");

        for (String emailToken : tokens) {
            if (emailToken.equals(token)) {
                return true;
            }
        }

        return false;
    }


    /**
     * 발신할 이메일 데이터 세팅
     */
    public void sendEmail(String toEmail) {
        SimpleMailMessage emailForm = new SimpleMailMessage();

        //이메일 인증코드 생성
        String token = createEmailAuthCode();

        //이메일 세팅
        emailForm.setTo(toEmail);
        emailForm.setSubject(" GiveBacks - 이메일 인증입니다.");
        emailForm.setText(
                " 이메일 인증입니다. \n 아래의 코드를 입력해주세요. \n" + token
        );


        // 이메일 토큰 redis 저장
        emailTokenSave(token);


        //이메일 보내기
        try {
            javaMailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.debug("MailService.sendEmail exception occur toEmail: {}, " +
                    "title: {}, text: {}", toEmail, emailForm.getSubject(), emailForm.getText());

        }
    }


}
