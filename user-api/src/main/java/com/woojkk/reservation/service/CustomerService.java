package com.woojkk.reservation.service;

import com.woojkk.reservation.domain.model.Customer;
import com.woojkk.reservation.domain.model.Manager;
import com.woojkk.reservation.domain.model.SignUpForm;
import com.woojkk.reservation.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form) {
        return customerRepository.save(Customer.from(form));
    }

    public boolean isExistEmail(String email) {
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }

    public Optional<Customer> findValidUser(String email, String password) {
        return customerRepository.findByEmail(email)
                .stream().filter(
                        customer -> customer.getPassword().equals(password)
                ).findFirst();
    }
}
