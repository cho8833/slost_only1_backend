package com.slost_only1.slost_only1.service.impl;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.model.NotificationLog;
import com.slost_only1.slost_only1.repository.NotificationLogRepository;
import com.slost_only1.slost_only1.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationLogRepository notificationLogRepository;


    @Override
    public void sendMessage(Member to, String title, String body) throws FirebaseMessagingException {


        String message = FirebaseMessaging.getInstance().send(Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .setToken(to.getFcmToken())  // 대상 디바이스의 등록 토큰
                .build());

        NotificationLog log = NotificationLog.builder()
                .to(to)
                .title(title)
                .body(body)
                .sentAt(LocalDateTime.now())
                .build();
        notificationLogRepository.save(log);

    }
}
