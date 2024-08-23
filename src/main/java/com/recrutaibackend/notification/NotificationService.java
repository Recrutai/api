package com.recrutaibackend.notification;

public interface NotificationService<T> {

    void send(T message);

}
