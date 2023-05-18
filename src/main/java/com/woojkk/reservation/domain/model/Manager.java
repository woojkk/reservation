package com.woojkk.reservation.domain.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String email;
    private String location;

    private String info;

    public static Manager from(SignUpForm form) {
        return Manager.builder()
                .name(form.getName())
                .password(form.getPassword())
                .email(form.getEmail())
                .location(form.getLocation())
                .build();
    }
}
