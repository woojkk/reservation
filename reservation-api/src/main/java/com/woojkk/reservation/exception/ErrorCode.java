package com.woojkk.reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {


    ALREADY_REGISTER_STORE(HttpStatus.BAD_REQUEST, "이미 등록된 매장입니다."),
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "일치하는 매장이 없습니다."),
    NOT_MATCHING_USERTYPE(HttpStatus.BAD_REQUEST, "매장 관리자만 매장을 등록할 수 있습니다."),

    RESERVATION_FAIL(HttpStatus.BAD_REQUEST, "예약할 수 없습니다."),

    // login

    LOGIN_CHECK_FAIL(HttpStatus.BAD_REQUEST, "아이디 또는 패스워드를 확인해주세요.");

    private final HttpStatus httpStatus;
    private final String detail;
}
