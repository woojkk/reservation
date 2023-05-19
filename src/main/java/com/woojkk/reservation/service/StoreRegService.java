package com.woojkk.reservation.service;

import com.woojkk.reservation.domain.model.RegistrationForm;
import com.woojkk.reservation.domain.model.Store;
import com.woojkk.reservation.domain.repository.StoreRegRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreRegService {

    private final StoreRegRepository storeRegRepository;

    public boolean isRegNumberExist(String registrationNumber) {
        return storeRegRepository.findByRegistrationNumber(registrationNumber)
                .isPresent();
    }

    public Store StoreRegistration(RegistrationForm form) {

        return storeRegRepository.save(Store.from(form));
    }
}
