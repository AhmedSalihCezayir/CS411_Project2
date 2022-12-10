package com.cs411project2.instantMessageApp.controller;

import com.cs411project2.instantMessageApp.model.AddFriendRequest;
import com.cs411project2.instantMessageApp.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("chat")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        return message;
    }

    @PostMapping("add_friend")
    public boolean addFriend(@Payload AddFriendRequest addFriendRequest){
       return addFriendSql(addFriendRequest);
    }

    public boolean addFriendSql(AddFriendRequest addFriendRequest) {
        String sql = "INSERT INTO friend (user1, user2) VALUES (?, ?)";

        try {
            jdbcTemplate.update(sql, addFriendRequest.getUser1(), addFriendRequest.getUser2());
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
