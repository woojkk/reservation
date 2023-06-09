package com.woojkk.reservation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {
    private String name;
    private String password;
    private String email;
    private LocalDate birth;
    private String phone;
}
