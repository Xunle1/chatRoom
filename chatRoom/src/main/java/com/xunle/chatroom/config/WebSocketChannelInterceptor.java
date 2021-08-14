package com.xunle.chatroom.config;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import javax.servlet.http.HttpSession;

/**
 * 暂时没用
 * @author xunle
 */
@Slf4j
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        log.info("============preSend=============");
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = accessor.getNativeHeader("chat_token").get(0);
            System.out.println("打开连接: " + token);
        } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
            System.out.println("断开连接");
        }

        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        log.info("===============postSend===============");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        HttpSession httpSession = (HttpSession)accessor.getSessionAttributes().get("HTTP_SESSION");

        System.out.println("postSend中获取httpSession key：" + httpSession.getId());

        if (accessor.getCommand() == null) {
            return ;
        }

        System.out.println(accessor.getCommand());
        switch (accessor.getCommand()) {
            case CONNECT:
                log.info("httpsession key: " + httpSession.getId() + ",首次链接");
                break;
            case CONNECTED:
                break;
            case DISCONNECT:
                log.info("httpsession key: " + httpSession.getId() + ",下线");
                break;
            default:
                break;
        }
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {

    }
}
