package com.example.cleanarchitecture.api.error;

import lombok.Getter;

@Getter
public enum ErrorEnum {

    general_internalServerError(50001, 500, "error_general_internal_server_error"),

    general_badRequest(40001, 400, "error_general_bad_request"),
    try_again_badRequest(40002, 400, "error_try_again"),

    unauthorized(40101, 401, "error_unauthorized"),

    general_notFound(40401, 404, "error_not_found"),
    employee_notFound(40402, 404, "error_employee_not_found"),
    organization_notFound(40403, 404, "error_organization_not_found"),

    access_denied(40301, 403, "error_access_denied"),
    ;

    // The code should be unique
    private final int code;
    private final int status;
    private final String messageKey;

    ErrorEnum(int code, int status, String messageKey) {
        this.code = code;
        this.status = status;
        this.messageKey = messageKey;
    }

}
