package com.misterdiallo.backend.springwebsocketchat.service;

import com.misterdiallo.backend.springwebsocketchat.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final  SimpMessagingTemplate simpMessagingTemplate;

    private final NotificationService notificationService;

    @Autowired
    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate, NotificationService notificationService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.notificationService = notificationService;
    }

    public void notifyFrontend(String message) {
        ResponseModel responseModel = new ResponseModel(message);
        notificationService.sendGlobalNotification();
        simpMessagingTemplate.convertAndSend("/topic/messages", responseModel);
    }

    public void notifyUser(final String userid, final String message) {
        ResponseModel responseModel = new ResponseModel(message);
        notificationService.sendPrivateNotification(userid);
        simpMessagingTemplate.convertAndSendToUser(userid, "/topic/private-messages", responseModel);
    }
}
