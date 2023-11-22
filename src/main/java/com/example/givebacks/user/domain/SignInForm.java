package com.example.givebacks.user.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInForm {

    private String userEmail;
    private String userPassword;
}
