package com.misterdiallo.backend.springwebsocketchat.controller;

import com.misterdiallo.backend.springwebsocketchat.model.MessageModel;
import com.misterdiallo.backend.springwebsocketchat.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody MessageModel messageModel) {
        webSocketService.notifyFrontend(messageModel.getMessageBody());
    }


    @PostMapping("/send-private-message/{userid}")
    public void sendPrivateMessage(@PathVariable String userid, @RequestBody MessageModel messageModel) {
        webSocketService.notifyUser(userid, messageModel.getMessageBody());
    }
}
