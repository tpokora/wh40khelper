package org.tpokora.wh40khelper.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class RestApiRequestException extends RestApiException {

    public RestApiRequestException(HttpStatus status, String message, List<String> errors) {
        super(status, message, errors);
    }

    public RestApiRequestException(HttpStatus status, String message, String error) {
        super(status, message, error);
    }
}
