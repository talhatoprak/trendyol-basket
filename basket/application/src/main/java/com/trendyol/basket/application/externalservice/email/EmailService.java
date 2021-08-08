package com.trendyol.basket.application.externalservice.email;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
