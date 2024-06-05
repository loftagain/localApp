package com.company.localApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<Object> handleBadRequest(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>("Invalid request parameters", HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler(value = { UnauthorizedException.class })
//    protected ResponseEntity<Object> handleUnauthorized(UnauthorizedException ex, WebRequest request) {
//        return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(value = { ForbiddenException.class })
//    protected ResponseEntity<Object> handleForbidden(ForbiddenException ex, WebRequest request) {
//        return new ResponseEntity<>("Access forbidden", HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler(value = { Exception.class })
//    protected ResponseEntity<Object> handleInternalServerError(Exception ex, WebRequest request) {
//        return new ResponseEntity<>("An error occurred while retrieving users", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
