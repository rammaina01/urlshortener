package com.url.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseException {
    private int status;
    private String msg;
    private HttpStatus httpStatus;
}
