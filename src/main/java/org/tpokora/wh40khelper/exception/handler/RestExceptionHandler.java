package org.tpokora.wh40khelper.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.tpokora.wh40khelper.exception.ItemAlreadyExistsException;
import org.tpokora.wh40khelper.exception.ItemNotFoundException;
import org.tpokora.wh40khelper.exception.RestApiException;
import org.tpokora.wh40khelper.exception.RestApiRequestException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        RestApiException apiError =
                new RestApiException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler({ IllegalArgumentException.class, ItemAlreadyExistsException.class, ItemNotFoundException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            RuntimeException ex, WebRequest request) {
        String error = ex.getClass().getName() + ": " + ex.getMessage();

        RestApiRequestException restApiRequestException =
                new RestApiRequestException(resolveHttpStatus(ex), ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(
                restApiRequestException, new HttpHeaders(), restApiRequestException.getStatus());
    }

    private HttpStatus resolveHttpStatus(RuntimeException exception) {
        if (exception instanceof ItemAlreadyExistsException) {
            return HttpStatus.CONFLICT;
        } else if (exception instanceof ItemNotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}