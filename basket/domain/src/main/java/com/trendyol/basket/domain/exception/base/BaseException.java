package com.trendyol.basket.domain.exception.base;

public class BaseException extends RuntimeException{
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }
}
