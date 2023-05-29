package com.woojkk.reservation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStoreForm {
    private Long id;
    private String name;
    private String location;
    private String storeInfo;
    private String registrationNumber;
    private Long price;
}
