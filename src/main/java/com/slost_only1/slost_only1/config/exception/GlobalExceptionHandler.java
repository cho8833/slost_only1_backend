package com.slost_only1.slost_only1.config.exception;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response<?>> customExceptionHandler(CustomException e) {
        Response<?> response = new Response<>();
        log.error(e.getMessage());
        response.setStatus(e.getErrorCode().getStatus());
        response.setCode(e.getErrorCode().name());
        response.setError(true);
        response.setMessage(e.getMessage());
        return ResponseEntity.status(200).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<?>> exceptionHandler(HttpServletRequest req, Exception e) {
        Response<?> response = new Response<>();
        log.error(e.toString());
        response.setStatus(ResponseCode.INTERNAL_SERVER_ERROR.getStatus());
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR.name());
        response.setError(true);
        response.setMessage(e.getMessage());
        return ResponseEntity.status(200).body(response);
    }
}

