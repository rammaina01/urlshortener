package com.url.exception;


import org.springframework.http.HttpStatus;

public class UrlAlreadyExistException extends RuntimeException{

    private static final long serialVersionUID = 8963763722782635261L;
    private int status;
    private String msg;
    private HttpStatus httpStatus;

    public UrlAlreadyExistException(int status, String msg, HttpStatus httpStatus){
        super(msg);
        this.status = status;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
