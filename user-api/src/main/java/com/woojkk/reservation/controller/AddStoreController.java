package com.woojkk.reservation.controller;

import com.woojkk.domain.common.UserType;
import com.woojkk.domain.common.UserVo;
import com.woojkk.domain.config.JwtAuthenticationProvider;
import com.woojkk.reservation.domain.model.AddStoreForm;
import com.woojkk.reservation.domain.model.StoreDto;
import com.woojkk.reservation.service.StoreRegService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/store")
@RequiredArgsConstructor
public class AddStoreController {

    private final StoreRegService storeRegService;
    private final JwtAuthenticationProvider provider;

    @PostMapping
    public ResponseEntity<StoreDto> addStore(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                             @RequestBody AddStoreForm form) {
        UserVo userVo = provider.getUserVo(token);
        if (userVo.getUserType() == UserType.MANAGER) {
            return ResponseEntity.ok(
                    StoreDto.from(storeRegService.addStore(
                            provider.getUserVo(token).getGetId(), form)));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
