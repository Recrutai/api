package com.recrutai.api.notification.email;

public record Email(
        String from,
        String to,
        String subject,
        String body
) {
}