package com.woojkk.reservation.service;

import com.woojkk.reservation.client.RedisClient;
import com.woojkk.reservation.domain.model.AddStoreCartForm;
import com.woojkk.reservation.domain.redis.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {

    private RedisClient redisClient;

    public Cart addCart(Long customerId, AddStoreCartForm form) {
        Cart cart = redisClient.get(customerId, Cart.class);


        if (cart == null) {
            cart = new Cart();
            cart.setCustomerId(customerId);
        }

        Optional<Cart.Store> productOptional = cart.getStores().stream()
                .filter(store1 -> store1.getId()
                        .equals(form.getId()))
                .findFirst();

        if (productOptional.isPresent()) {
            Cart.Store redisStore = productOptional.get();


            if (!redisStore.getName().equals(form.getName())) {
                cart.addMessage(redisStore.getName() + "의 정보가 변경되었습니다.");
            }


        } else {
            Cart.Store store = Cart.Store.from(form);
            cart.getStores().add(store);
        }

        redisClient.put(customerId, cart);

        return cart;

    }
}
