package com.woojkk.reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {


    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    ALREADY_REGISTER_STORE(HttpStatus.BAD_REQUEST, "이미 등록된 매장입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "일치하는 회원이 없습니다."),

    // login

    LOGIN_CHECK_FAIL(HttpStatus.BAD_REQUEST, "아이디 또는 패스워드를 확인해주세요.");

    private final HttpStatus httpStatus;
    private final String detail;
}
