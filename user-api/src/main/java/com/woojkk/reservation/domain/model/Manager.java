package com.woojkk.reservation.domain.model;

import com.woojkk.reservation.domain.entity.BaseEntity;
import lombok.*;
import org.hibernate.envers.AuditOverride;

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
@AuditOverride(forClass = BaseEntity.class)
public class Manager extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String email;


    public static Manager from(SignUpForm form) {
        return Manager.builder()
                .name(form.getName())
                .password(form.getPassword())
                .email(form.getEmail())
                .build();
    }
}
