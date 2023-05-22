package com.woojkk.reservation.service;

import com.woojkk.reservation.domain.model.AddStoreForm;
import com.woojkk.reservation.domain.model.Store;
import com.woojkk.reservation.domain.repository.StoreRegRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreRegService {

    private final StoreRegRepository storeRegRepository;

    public boolean isRegNumberExist(String registrationNumber) {
        return storeRegRepository.findByRegistrationNumber(registrationNumber)
                .isPresent();
    }

    @Transactional
    public Store addStore(Long managerId, AddStoreForm form) {

        return storeRegRepository.save(Store.of(managerId, form));
    }
}
