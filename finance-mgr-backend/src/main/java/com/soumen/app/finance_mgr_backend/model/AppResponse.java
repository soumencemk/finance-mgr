package com.soumen.app.finance_mgr_backend.model;

public record AppResponse(String statusCode, ResponseStatus responseStatus,String failReason) {
}

