package com.elmar.rest.practice.restking.api.error;

import com.elmar.rest.practice.restking.bussiness.service.AppUserNotFoundException;
import com.elmar.rest.practice.restking.bussiness.service.AuthenticationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.UNAUTHORIZED);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage("N/A");
        apiError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(AppUserNotFoundException.class)
    protected ResponseEntity<Object> handleAppUserNotFoundException(AppUserNotFoundException ex) {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage("N/A");
        apiError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

}
