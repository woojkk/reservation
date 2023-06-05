package com.woojkk.reservation.service;

import com.woojkk.reservation.domain.model.Store;
import com.woojkk.reservation.domain.repository.StoreRepository;
import com.woojkk.reservation.exception.CustomException;
import com.woojkk.reservation.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreSearchService {

    private final StoreRepository storeRepository;

    public List<Store> searchByName(String name) {
        return storeRepository.searchByName(name);
    }

    public Store getByStoreId(Long storeId) {
        return storeRepository.findWithStoreById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    }

//    public List<Store> getListByStoreIds(List<Long> storeIds) {
//        return storeRepository.findAllById(storeIds);
//    }
}
