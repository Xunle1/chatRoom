package com.xunle.chatroom.controller;

import com.xunle.chatroom.entity.MessageRequest;
import com.xunle.chatroom.entity.MessageResponse;
import com.xunle.chatroom.handler.ServiceException;
import com.xunle.chatroom.service.MessageService;
import com.xunle.chatroom.service.RoomService;
import com.xunle.chatroom.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


/**
 * @author xunle
 */
@Controller
@Slf4j
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageService messageService;
    @Autowired
    private RoomService roomService;

    @MessageMapping("/massRequest/{roomId}")
    public String mass(@DestinationVariable("roomId")String roomId,
                       MessageRequest messageRequest) {
        log.info("========群发==========");
        System.out.println("userId: " + messageRequest.getUserId());
        System.out.println("messageType: " + messageRequest.getType());
        System.out.println("message: " + messageRequest.getContent());

        messageService.insertMessage(messageRequest.getUserId(),roomId,messageRequest.getContent(), messageRequest.getType());
        MessageResponse response = new MessageResponse(messageRequest.getUserId(),
                messageRequest.getType(),
                messageRequest.getContent());
        
        messagingTemplate.convertAndSend("/mass/getResponse/" + roomId, response);
        return messageRequest.getContent();
    }

    @MessageMapping("/aloneRequest/{toId}")
    public String sendMsgById(@DestinationVariable("toId")String toId, MessageRequest messageRequest) {
        log.info("==============sendMsgById====================");
        System.out.println("userId: " + messageRequest.getUserId());
        System.out.println("message: " + messageRequest.getContent());

        boolean flag = messageService.insertMessage(messageRequest.getUserId(),
                toId,
                messageRequest.getContent(),
                messageRequest.getType());
        MessageResponse response = new MessageResponse(messageRequest.getUserId(),
                messageRequest.getType(),
                messageRequest.getContent());
        if (flag) {
            this.messagingTemplate.convertAndSendToUser(toId+"","/alone/getResponse", response);
        } else {
            throw new ServiceException(ResultCode.BAD_REQUEST.getCode(), ResultCode.BAD_REQUEST.getMessage());
        }

        return messageRequest.getContent();
    }
}
