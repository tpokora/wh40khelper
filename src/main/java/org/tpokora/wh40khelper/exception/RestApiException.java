package org.tpokora.wh40khelper.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class RestApiException extends RuntimeException {

    private final HttpStatus status;
    private final String message;
    private final List<String> errors;

    public RestApiException() {
        this.status = null;
        this.message = null;
        this.errors = null;
    }

    public RestApiException(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public RestApiException(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Collections.singletonList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}