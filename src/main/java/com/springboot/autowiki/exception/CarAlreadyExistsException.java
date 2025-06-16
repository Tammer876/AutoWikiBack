package com.springboot.autowiki.exception;


public class CarAlreadyExistsException extends RuntimeException {
    public CarAlreadyExistsException(String message) {
        super(message);
    }
}
