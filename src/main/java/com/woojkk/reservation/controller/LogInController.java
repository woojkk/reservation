package com.woojkk.reservation.controller;

import com.woojkk.reservation.application.LogInApplication;
import com.woojkk.reservation.domain.model.LogInForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LogInController {

    private final LogInApplication logInApplication;

    @PostMapping("/manager")
    public ResponseEntity<String> signInManager(@RequestBody LogInForm form) {
        return ResponseEntity.ok(logInApplication.ManagerLoginToken(form));
    }



}
