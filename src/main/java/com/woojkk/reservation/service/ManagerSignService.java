package com.woojkk.reservation.service;

import com.woojkk.reservation.domain.model.Manager;
import com.woojkk.reservation.domain.model.SignUpForm;
import com.woojkk.reservation.domain.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ManagerSignService {

    private final ManagerRepository managerRepository;

    public Manager signUp(SignUpForm form) {
        return managerRepository.save(Manager.from(form));
    }

    public boolean isExistEmail(String email) {
        return managerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }
}
