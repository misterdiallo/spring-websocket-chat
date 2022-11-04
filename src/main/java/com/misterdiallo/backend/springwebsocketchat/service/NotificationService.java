package com.misterdiallo.backend.springwebsocketchat.service;

import com.misterdiallo.backend.springwebsocketchat.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGlobalNotification() {
        ResponseModel message = new ResponseModel("Global Notification");
        messagingTemplate.convertAndSend("/topic/global-notifications", message);
    }

    public void sendPrivateNotification(final String userId) {
        ResponseModel message = new ResponseModel("Private Notification");
        messagingTemplate.convertAndSendToUser(userId, "/topic/private-notifications", message);
    }
}
