package com.woojkk.reservation.service;

import com.woojkk.reservation.domain.model.AddStoreForm;
import com.woojkk.reservation.domain.model.Store;
import com.woojkk.reservation.domain.model.UpdateStoreForm;
import com.woojkk.reservation.domain.repository.StoreRepository;
import com.woojkk.reservation.exception.CustomException;
import com.woojkk.reservation.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreRegService {

    private final StoreRepository storeRepository;

    public boolean isRegNumberExist(String registrationNumber) {
        return storeRepository.findByRegistrationNumber(registrationNumber)
                .isPresent();
    }

    @Transactional
    public Store addStore(AddStoreForm form) {

        return storeRepository.save(Store.of(form));
    }

    @Transactional
    public Store updateStore(UpdateStoreForm form) {
        Store store = storeRepository.findById(form.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        store.setName(form.getName());
        store.setLocation(form.getLocation());
        store.setStoreInfo(form.getStoreInfo());
        store.setRegistrationNumber(form.getRegistrationNumber());
        store.setPrice(form.getPrice());

        return store;
    }

    @Transactional
    public void deleteStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        storeRepository.delete(store);
    }
}
