package com.trendyol.basket.application.converter;

public interface Converter<T,R> {
    R convert(T t);
}
