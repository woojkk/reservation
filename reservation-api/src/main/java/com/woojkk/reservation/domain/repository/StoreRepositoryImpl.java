package com.woojkk.reservation.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.woojkk.reservation.domain.model.QStore;
import com.woojkk.reservation.domain.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Store> searchByName(String name) {
        String search = "%" + name + "%";

        QStore store = QStore.store;

        return jpaQueryFactory.selectFrom(store)
                .where(store.name.like(search))
                .fetch();
    }
}
