package br.com.zupacademy.marciodegan.propostas.compartilhado.exception;

import org.springframework.http.HttpStatus;

public class FeignExceptionError extends RuntimeException{

    private final HttpStatus httpStatus;
    private final String reason;

    public FeignExceptionError(HttpStatus httpStatus, String reason) {
        super(reason);
        this.httpStatus = httpStatus;
        this.reason = reason;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getReason() {
        return reason;
    }
}