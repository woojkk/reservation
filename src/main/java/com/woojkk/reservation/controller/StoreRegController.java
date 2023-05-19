package com.woojkk.reservation.controller;

import com.woojkk.reservation.application.StoreRegApplication;
import com.woojkk.reservation.domain.model.RegistrationForm;
import com.woojkk.reservation.domain.model.Store;
import com.woojkk.reservation.service.StoreRegService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreRegController {

    private final StoreRegApplication storeRegApplication;

    @PostMapping("/store")
    public ResponseEntity<String> StoreRegistration(@RequestBody RegistrationForm form) {

        return ResponseEntity.ok(storeRegApplication.StoreRegistration(form));
    }
}
