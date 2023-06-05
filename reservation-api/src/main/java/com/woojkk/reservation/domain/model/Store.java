package com.woojkk.reservation.domain.model;

import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@AuditOverride(forClass = BaseEntity.class)
@Audited
public class Store extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long storeId;
    private String name;
    private String location;
    private String storeInfo;
    private String registrationNumber;
    private Long price;




    public static Store of(Long storeId,AddStoreForm form) {
        return Store.builder()
                .storeId(storeId)
                .name(form.getName())
                .location(form.getLocation())
                .storeInfo(form.getStoreInfo())
                .registrationNumber(form.getRegistrationNumber())
                .price(form.getPrice())
                .build();
    }
}
