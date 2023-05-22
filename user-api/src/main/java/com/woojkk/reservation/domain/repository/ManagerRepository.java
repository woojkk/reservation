package com.woojkk.reservation.domain.repository;

import com.woojkk.reservation.domain.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByIdAndEmail(Long id, String email);

    Optional<Manager> findByEmail(String email);
}
