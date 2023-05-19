package com.woojkk.reservation.service;

import com.woojkk.reservation.domain.model.Manager;
import com.woojkk.reservation.domain.model.SignUpForm;
import com.woojkk.reservation.domain.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

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

    public Optional<Manager> findValidCustomer(String email, String password) {
        return managerRepository.findByEmail(email)
                .stream().filter(
                        manager -> manager.getPassword().equals(password)
                ).findFirst();
    }
}
