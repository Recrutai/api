package com.recrutai.api.notification.email;

import lombok.Getter;

@Getter
public class Email {
    private final String from;
    private final String to;
    private final String subject;
    private final String body;

    public Email(String from, String to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

}
