package com.woojkk.reservation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddStoreForm {

    private String name;
    private String location;
    private String storeInfo;
    private String registrationNumber;


}
