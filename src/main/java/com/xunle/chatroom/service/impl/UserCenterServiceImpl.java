package com.xunle.chatroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunle.chatroom.entity.User;
import com.xunle.chatroom.entity.vo.UserLoginVo;
import com.xunle.chatroom.handler.ServiceException;
import com.xunle.chatroom.mapper.UserMapper;
import com.xunle.chatroom.service.UserCenterService;
import com.xunle.chatroom.service.UserService;
import com.xunle.chatroom.utils.JwtUtils;
import com.xunle.chatroom.utils.MD5;
import com.xunle.chatroom.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author xunle
 */
@Service
@Slf4j
public class UserCenterServiceImpl extends ServiceImpl<UserMapper, User> implements UserCenterService {
    @Autowired
    private UserService userService;

    @Override
    public String login(UserLoginVo user) {
        String username = user.getUsername();
        String password = user.getPassword();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ServiceException(20001, "用户名或密码不能为空");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User loginUser = userService.getOne(wrapper);
        //判断用户名是否存在
        if (null == loginUser) {
            log.error("error -------> 用户名不存在");
            throw new ServiceException(ResultCode.USERNAME_OR_PASSWORD_ERROR.getCode(),
                    ResultCode.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }

        //判断密码
        if (!MD5.encrypt(password).equals(loginUser.getPassword())) {
            log.error("error -------> 密码错误");
            throw new ServiceException(ResultCode.USERNAME_OR_PASSWORD_ERROR.getCode(),
                    ResultCode.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }

        String jwtToken = JwtUtils.getJwtToken(loginUser.getId(),loginUser.getUsername());
        return jwtToken;
    }

    @Override
    public boolean register(User registerUser) throws ServiceException {
        String username = registerUser.getUsername();
        String email = registerUser.getEmail();
        String password = registerUser.getPassword();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new ServiceException(20001,"用户名、邮箱和密码不能为空");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        Integer count = userService.count(wrapper);
        if (count > 0) {
            throw new ServiceException(ResultCode.USERNAME_EXISTS.getCode(), ResultCode.USERNAME_EXISTS.getMessage());
        }
        
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(MD5.encrypt(password));
        userService.save(user);
        return true;
    }
}
