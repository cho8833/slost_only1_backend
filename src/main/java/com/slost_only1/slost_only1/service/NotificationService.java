package com.slost_only1.slost_only1.service;


import com.google.firebase.messaging.FirebaseMessagingException;
import com.slost_only1.slost_only1.model.Member;

public interface NotificationService {
    void sendMessage(Member to, String title, String body) throws FirebaseMessagingException;
}
