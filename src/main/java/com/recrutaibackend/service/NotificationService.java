package com.recrutaibackend.service;

public interface NotificationService<T> {

    void send(T message);

}
