package com.recrutai.api.notification;

public interface NotificationService<T> {

    void send(T message);

}
