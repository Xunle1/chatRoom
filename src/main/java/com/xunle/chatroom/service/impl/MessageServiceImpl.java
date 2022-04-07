package com.xunle.chatroom.service.impl;

import com.xunle.chatroom.entity.Message;
import com.xunle.chatroom.mapper.MessageMapper;
import com.xunle.chatroom.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xunle
 * @since 2021-08-04
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Override
    public boolean insertMessage(String fromId, String toId, String content, int type) {
        Message message = new Message();
        message.setFromId(fromId);
        message.setToId(toId);
        message.setContent(content);
        message.setType(type);
        message.setSendTime(new Date(System.currentTimeMillis()));
        int insert = baseMapper.insert(message);
        return insert > 0;
    }

}
