package com.slost_only1.slost_only1.base;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ResponseCode {
    /**
     * Global Code
     */
    //요청 성공
    OK(200, "OK", "요청 성공"),


    //Global Server Error
    INTERNAL_SERVER_ERROR(500,  "Internal Server Error", "의도 하지 않은 서버에러 발생"),

    //인증 만료
    UNAUTHORIZED(401, "unauthorized", "인증키 필요"),

    //권한 없음
    FORBIDDEN(403,  "Forbidden", "권한 없음"),

    //데이터 찾을수 없을경우 사용
    DATA_NOT_FOUND(500,  "데이터를 찾을 수 없습니다.", "데이터를 찾을수 없는 경우"),

    //수정 권한 없음
    NOT_PERMISSION(422,"수정 권한이 없습니다.", "수정권한이 없습니다.");



    private static final List<ResponseCode> COMMON_SUCCESS_CODES = Collections.singletonList(OK);

    public static List<ResponseCode> getCommonSuccessCodes() {
        return COMMON_SUCCESS_CODES;
    }

    /**
     * 공통으로 사용될 에러코드
     */
    private static final List<ResponseCode> COMMON_ERROR_CODES = Arrays.asList(
              INTERNAL_SERVER_ERROR
            , UNAUTHORIZED
            , FORBIDDEN
            , DATA_NOT_FOUND
            , NOT_PERMISSION
    );

    public static List<ResponseCode> getCommonErrorCodes() {
        return COMMON_ERROR_CODES;
    }


    @Deprecated
    private final int status;
    private final String message;
    private final String descr;

    ResponseCode(int status, String message, String descr) {
        this.status = status;
        this.message = message;
        this.descr = descr;
    }
    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public String getDescr() {
        return descr;
    }



}
