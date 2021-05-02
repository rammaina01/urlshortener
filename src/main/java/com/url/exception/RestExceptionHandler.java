package com.url.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UrlAlreadyExistException.class)
    public ResponseEntity<ResponseException> urlExistException(UrlAlreadyExistException ex){
        ResponseException exception = new ResponseException(ex.getStatus(), ex.getMsg(), ex.getHttpStatus());
        return new ResponseEntity<>(exception, ex.getHttpStatus());
    }
}
