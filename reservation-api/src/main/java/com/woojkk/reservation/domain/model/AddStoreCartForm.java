package com.woojkk.reservation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddStoreCartForm {
    private Long Id;
    private Long managerId;
    private String name;
    private String location;
    private String storeInfo;
    private String registrationNumber;
    private Long price;

}
