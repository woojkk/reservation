package com.woojkk.reservation.application;

import com.woojkk.reservation.config.JwtAuthenticationProvider;
import com.woojkk.reservation.config.UserType;
import com.woojkk.reservation.domain.model.Manager;
import com.woojkk.reservation.domain.model.LogInForm;
import com.woojkk.reservation.exception.CustomException;
import com.woojkk.reservation.exception.ErrorCode;
import com.woojkk.reservation.service.ManagerSignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogInApplication {

    private final ManagerSignService managerSignService;

    private final JwtAuthenticationProvider provider;

    public String ManagerLoginToken(LogInForm form) {
        Manager loginCheck = managerSignService
                .findValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));

        return provider.createToken(
                loginCheck.getEmail(),
                loginCheck.getId(),
                UserType.MANAGER);
    }

}
