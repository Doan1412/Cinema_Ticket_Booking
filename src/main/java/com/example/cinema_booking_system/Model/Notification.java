package com.example.cinema_booking_system.Model;

import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;

public class Notification {
    private int notificationId;
    private String message;
    private LocalDateTime sentDate;
    @OneToMany
    private Customer recipient;

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public Customer getRecipient() {
        return recipient;
    }

    public void setRecipient(Customer recipient) {
        this.recipient = recipient;
    }
}
