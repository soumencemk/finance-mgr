package com.soumen.app.finance_mgr_backend.exception;

import com.soumen.app.finance_mgr_backend.model.AppResponse;
import com.soumen.app.finance_mgr_backend.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppResponse> handleException(Exception e) {
        log.error("Handling Error : ",e);
        return ResponseUtil.failureResponse(e);
    }
}
