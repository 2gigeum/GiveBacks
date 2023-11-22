package com.example.givebacks.user.domain;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpForm {

    private String userEmail;
    private String userPassword;
    private String userName;
    private String userNickname;
}
