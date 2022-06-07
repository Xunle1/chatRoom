package com.xunle.chatroom.controller;

import com.xunle.chatroom.entity.User;
import com.xunle.chatroom.entity.vo.UserLoginVo;
import com.xunle.chatroom.service.UserCenterService;
import com.xunle.chatroom.utils.JwtUtils;
import com.xunle.chatroom.common.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xunle
 */
@RestController
@RequestMapping("/chatroom")
@Api(tags = "用户登录")
@CrossOrigin
public class UserCenterController {

    @Autowired
    private UserCenterService userCenterService;

    @PostMapping("/login")
    public Response loginUser(@RequestBody UserLoginVo user) {
        String token = userCenterService.login(user);
        if (token != null && token.length() > 0) {
            return Response.ok().data("token", token);
        } else {
            return Response.error();
        }
    }

    @PostMapping("/register")
    public Response register(@RequestBody User user) {
        boolean flag = userCenterService.register(user);
        if (flag) {
            return Response.ok();
        } else {
            return Response.error();
        }
    }

    //根据token获取用户信息
    @GetMapping("/userinfo")
    public Response getUserInfo(HttpServletRequest request) {
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        User user = userCenterService.getById(userId);
        return Response.ok().data("id", userId)
                .data("name", user.getUsername())
                .data("roles", "admin")
                .data("avatar", user.getAvatar())
                .data("sign", user.getSign());
    }

}
