package com.soumen.app.finance_mgr_backend.util;

import com.soumen.app.finance_mgr_backend.model.AppResponse;
import com.soumen.app.finance_mgr_backend.model.ResponseStatus;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    private static final String status_success = "S1";
    static AppResponse SUCCESS = new AppResponse(status_success, ResponseStatus.SUCCESS, null);

    public static ResponseEntity<AppResponse> successResponse() {
        return ResponseEntity.ok(SUCCESS);
    }

    public static ResponseEntity<AppResponse> failureResponse(Exception e) {
        ProblemDetail problemDetail;
        if (e instanceof DataIntegrityViolationException d) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, d.getMostSpecificCause()
                    .getMessage());
            problemDetail.setTitle(d.getMostSpecificCause()
                    .getMessage());
        } else {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
            problemDetail.setTitle(e.getMessage());
        }
        return ResponseEntity.of(problemDetail)
                .build();
    }
}
