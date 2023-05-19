package com.woojkk.reservation.application;

import com.woojkk.reservation.domain.model.RegistrationForm;
import com.woojkk.reservation.domain.model.Store;
import com.woojkk.reservation.exception.CustomException;
import com.woojkk.reservation.exception.ErrorCode;
import com.woojkk.reservation.service.StoreRegService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreRegApplication {

    private final StoreRegService storeRegService;

    public String StoreRegistration(RegistrationForm form) {
        if (storeRegService.isRegNumberExist(form.getRegistrationNumber())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_STORE);
        } else {
            storeRegService.StoreRegistration(form);
        }
        return "매장 등록에 성공하였습니다.";
    }
}
