package com.jpa.api.global.enums;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    // 멤버 관련 에러
    E10001("멤버 생성 데이터가 잘못 되었습니다.", BAD_REQUEST),
    E10002("멤버를 찾을 수 없습니다.", NOT_FOUND),
    E10003("중복된 사용자명입니다.", CONFLICT),
    E10004("중복된 이메일입니다.", CONFLICT),
    E10005("멤버 업데이트 실패.", BAD_REQUEST),
    E10006("멤버 삭제 실패.", BAD_REQUEST),
    
    // 팀 관련 에러
    E20001("팀 생성 데이터가 잘못 되었습니다.", BAD_REQUEST),
    E20002("팀을 찾을 수 없습니다.", NOT_FOUND),
    E20003("중복된 팀명입니다.", CONFLICT),
    E20004("팀 업데이트 실패.", BAD_REQUEST),
    E20005("팀 삭제 실패.", BAD_REQUEST),
    
    // 일반 에러
    E99999("예상치 못한 오류가 발생했습니다.", INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;
}
