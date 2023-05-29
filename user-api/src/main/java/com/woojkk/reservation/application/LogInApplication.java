package com.woojkk.reservation.application;

import com.woojkk.reservation.domain.model.Customer;
import com.woojkk.reservation.domain.model.LogInForm;
import com.woojkk.reservation.domain.model.Manager;
import com.woojkk.reservation.exception.CustomException;
import com.woojkk.reservation.exception.ErrorCode;
import com.woojkk.reservation.service.CustomerService;
import com.woojkk.reservation.service.ManagerService;
import domain.common.UserType;
import domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogInApplication {

    private final ManagerService managerService;

    private final CustomerService customerService;

    private final JwtAuthenticationProvider provider;

    public String managerLoginToken(LogInForm form) {
        Manager loginCheck = managerService
                .findValidUser(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));

        return provider.createToken(
                loginCheck.getEmail(),
                loginCheck.getId(),
                UserType.MANAGER);
    }

    public String customerLoginToken(LogInForm form) {
        Customer loginCheck = customerService
                .findValidUser(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));

        return provider.createToken(
                loginCheck.getEmail(),
                loginCheck.getId(),
                UserType.CUSTOMER);
    }

}
