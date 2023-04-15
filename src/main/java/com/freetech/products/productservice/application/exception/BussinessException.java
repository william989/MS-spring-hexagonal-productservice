package com.freetech.products.productservice.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BussinessException extends RuntimeException {
    private String code;
    private String messageCause;
    private HttpStatus httpStatus;

    public BussinessException(String code, String message, String messageCause, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.messageCause = messageCause;
        this.httpStatus = httpStatus;
    }
}
