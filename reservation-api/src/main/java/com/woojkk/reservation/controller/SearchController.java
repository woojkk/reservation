package com.woojkk.reservation.controller;

import com.woojkk.reservation.domain.model.StoreDto;
import com.woojkk.reservation.service.StoreSearchService;
import domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search/store")
@RequiredArgsConstructor
public class SearchController {
    private final StoreSearchService storeSearchService;

    @GetMapping("")
    public ResponseEntity<List<StoreDto>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(
                storeSearchService.searchByName(name).stream()
                        .map(StoreDto::from).collect(Collectors.toList()));
    }

}
