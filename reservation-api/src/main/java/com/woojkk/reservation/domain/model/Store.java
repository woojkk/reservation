package com.woojkk.reservation.domain.model;

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
public class Store extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String storeInfo;
    private String registrationNumber;
    private Long price;

    public static Store of(AddStoreForm form) {
        return Store.builder()
                .name(form.getName())
                .location(form.getLocation())
                .storeInfo(form.getStoreInfo())
                .registrationNumber(form.getRegistrationNumber())
                .price(form.getPrice())
                .build();
    }
}
