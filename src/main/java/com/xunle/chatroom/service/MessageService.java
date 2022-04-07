package com.xunle.chatroom.service;

import com.xunle.chatroom.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xunle
 * @since 2021-08-04
 */
public interface MessageService extends IService<Message> {

    boolean insertMessage(String fromId, String toId, String message, int type);
}
