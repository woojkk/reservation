package com.woojkk.reservation.domain.redis;

import com.woojkk.reservation.domain.model.AddStoreCartForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RedisHash("cart")
public class Cart {

    @Id
    private Long customerId;
    private List<Store> stores = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

    public void addMessage(String message) {
        messages.add(message);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Store{
        private Long id;
        private Long managerId;
        private String name;
        private String location;
        private String storeInfo;
        private String registrationNumber;
        private Long price;


        public static Store from(AddStoreCartForm form) {
            return Store.builder()
                    .id(form.getId())
                    .managerId(form.getManagerId())
                    .name(form.getName())
                    .storeInfo(form.getStoreInfo())
                    .build();
        }
    }

}
