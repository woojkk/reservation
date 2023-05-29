package com.woojkk.reservation.controller;

import com.woojkk.reservation.domain.model.AddStoreForm;
import com.woojkk.reservation.domain.model.StoreDto;
import com.woojkk.reservation.domain.model.UpdateStoreForm;
import com.woojkk.reservation.exception.CustomException;
import com.woojkk.reservation.exception.ErrorCode;
import com.woojkk.reservation.service.StoreRegService;
import domain.common.UserType;
import domain.common.UserVo;
import domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/store")
@RequiredArgsConstructor
public class ManagerStoreController {

    private final StoreRegService storeRegService;
    private final JwtAuthenticationProvider provider;

    @PostMapping
    public ResponseEntity<StoreDto> addStore(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                             @RequestBody AddStoreForm form) {
        UserVo userVo = provider.getUserVo(token);
        if (userVo.getUserType() == UserType.MANAGER) {
            if (storeRegService.isRegNumberExist(form.getRegistrationNumber())) {
                throw new CustomException(ErrorCode.ALREADY_REGISTER_STORE);
            } else {
                return ResponseEntity.ok(
                        StoreDto.from(storeRegService.addStore(form)));
            }
        } else {
            throw new CustomException(ErrorCode.NOT_MATCHING_USERTYPE);
        }
    }

    @PutMapping
    public ResponseEntity<StoreDto> updateStore(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                @RequestBody UpdateStoreForm form) {
        return ResponseEntity.ok(
                StoreDto.from(storeRegService.updateStore(form)));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStore(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                            @RequestParam Long id) {
        storeRegService.deleteStore(id);

        return ResponseEntity.ok().build();
    }
}
