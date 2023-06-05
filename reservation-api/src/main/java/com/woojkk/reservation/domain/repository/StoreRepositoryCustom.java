package com.woojkk.reservation.domain.repository;

import com.woojkk.reservation.domain.model.Store;

import java.util.List;


public interface StoreRepositoryCustom {
    List<Store> searchByName(String name);
}
