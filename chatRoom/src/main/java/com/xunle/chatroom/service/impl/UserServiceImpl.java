package com.xunle.chatroom.service.impl;

import com.xunle.chatroom.entity.User;
import com.xunle.chatroom.mapper.UserMapper;
import com.xunle.chatroom.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xunle
 * @since 2021-07-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
