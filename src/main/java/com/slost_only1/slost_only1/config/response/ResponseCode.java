package com.slost_only1.slost_only1.config.response;

public enum ResponseCode {
    //요청 성공
    OK(200, "OK", "요청 성공"),

    //Global Server Error
    INTERNAL_SERVER_ERROR(500,  "Internal Server Error", "의도 하지 않은 서버에러 발생"),

    // 인증 만료
    UNAUTHORIZED(401, "unauthorized", "인증키 필요"),
    WRONG_REQUEST(400, "wrong request", "잘못된 요청입니다"),
    NO_USER_FOUND(401, "No user found", "아이디 혹은 비밀번호가 잘못되었습니다"),

    NOT_USER(4002, "not user", "유저가 아닙니다"),
    CROSS_USER(4001, "Cross user", "교차 이용 불가"),
    DUPLICATE_USER(4003, "Duplicate user", "이미 존재하는 회원입니다"),

    FORBIDDEN(403,  "Forbidden", "권한 없음"),

    DATA_NOT_FOUND(404,  "Data not found", "데이터를 찾을 수 없습니다"),

    FAIL_UPLOAD_FILE(500, "failed to upload file", "파일 업로드에 실패했습니다"),

    // Kid
    NO_MORE_KID(550, "NO MORE KID", "아이는 최대 10명까지 추가 가능합니다");

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
