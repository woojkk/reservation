package com.woojkk.reservation.domain.repository;

import com.woojkk.reservation.domain.model.Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
    Optional<Store> findByRegistrationNumber(String registrationNumber);

    @EntityGraph(attributePaths = {"store"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Store> findWithStoreById(Long id);
}
