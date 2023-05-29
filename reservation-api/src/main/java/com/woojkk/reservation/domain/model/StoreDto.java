package com.woojkk.reservation.domain.model;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StoreDto {
    private Long id;
    private String name;
    private String location;
    private String storeInfo;
    private String registrationNumber;
    private Long price;


    public static StoreDto from(Store store) {
        return StoreDto.builder()
                .id(store.getId())
                .name(store.getName())
                .location(store.getLocation())
                .storeInfo(store.getStoreInfo())
                .registrationNumber(store.getRegistrationNumber())
                .price(store.getPrice())
                .build();
    }
}
