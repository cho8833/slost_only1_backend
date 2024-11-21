package com.slost_only1.slost_only1.config.exception;

import com.slost_only1.slost_only1.config.response.ResponseCode;

public class CustomException extends RuntimeException {

    ResponseCode errorCode;

    public CustomException(String message) {
        super(message);
        errorCode = ResponseCode.INTERNAL_SERVER_ERROR;
    }
    public CustomException(ResponseCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomException(ResponseCode errorCode) {
        super(errorCode.getDescr());
        this.errorCode = errorCode;
    }

    public ResponseCode getErrorCode() {
        return errorCode;
    }
}
