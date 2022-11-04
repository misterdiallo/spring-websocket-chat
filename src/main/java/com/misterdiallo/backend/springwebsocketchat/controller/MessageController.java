package com.misterdiallo.backend.springwebsocketchat.controller;

import com.misterdiallo.backend.springwebsocketchat.model.MessageModel;
import com.misterdiallo.backend.springwebsocketchat.model.ResponseModel;
import com.misterdiallo.backend.springwebsocketchat.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MessageController {

    @Autowired
    private NotificationService notificationService;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseModel getMessage(MessageModel messageModel) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.sendGlobalNotification();
        return new ResponseModel(HtmlUtils.htmlEscape(messageModel.getMessageBody()));
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseModel getPrivateMessage(MessageModel messageModel, final Principal principal) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.sendPrivateNotification(principal.getName());
        return new ResponseModel(HtmlUtils.htmlEscape("Sending private message to user: " + principal.getName() + " : " + messageModel.getMessageBody()));
    }



}
