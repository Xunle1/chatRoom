package com.xunle.chatroom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunle.chatroom.entity.User;
import com.xunle.chatroom.entity.vo.UserLoginVo;
import com.xunle.chatroom.handler.ServiceException;

/**
 * @author xunle
 */
public interface UserCenterService extends IService<User> {

    String login(UserLoginVo loginUser);

    boolean register(User user) throws ServiceException;
}
