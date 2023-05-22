package com.woojkk.reservation.domain.repository;

import com.woojkk.reservation.domain.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRegRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByRegistrationNumber(String registrationNumber);
}
