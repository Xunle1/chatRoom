package com.xunle.chatroom.controller;


import com.xunle.chatroom.entity.FriendReq;
import com.xunle.chatroom.entity.vo.FriendReqVO;
import com.xunle.chatroom.entity.vo.FriendVO;
import com.xunle.chatroom.service.FriendReqService;
import com.xunle.chatroom.service.FriendService;
import com.xunle.chatroom.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xunle
 * @since 2021-07-30
 */
@Api(tags = "好友管理")
@RestController
@RequestMapping("/chatroom")
@CrossOrigin
public class FriendController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private FriendReqService reqService;

    @ApiOperation("添加好友")
    @PostMapping("/friend")
    public Response addFriend(@RequestBody FriendReq friendReq) {
        boolean flag = friendService.sendAddRequest(friendReq);
        if (flag) {
            return Response.ok().message("您的好友请求已发送");
        } else {
            return Response.error().message("发送好友请求失败");
        }
    }

    @ApiOperation("查询好友列表")
    @GetMapping("/friends/{userId}")
    public Response getFriends(@PathVariable("userId") String userId) {
        List<FriendVO> friends = friendService.getFriends(userId);
        return Response.ok().data("friends", friends);
    }

    @ApiOperation("查询好友请求")
    @GetMapping("/friendreq/{userId}")
    public Response getRequests(@PathVariable("userId") String userId) {
        List<FriendReqVO> requests = reqService.getRequests(userId);
        return Response.ok().data("reqs", requests);
    }

    @ApiOperation("处理好友请求")
    @PostMapping("/friendreq")
    public Response handleRequest(@RequestBody FriendReq req) {
        boolean flag = reqService.handleRequest(req);
        if (flag) {
            return Response.ok().message("添加好友成功");
        } else {
            return Response.ok().message("拒绝添加");
        }
    }

    @ApiOperation("删除好友")
    @DeleteMapping("/friend/{userId}/{friendId}")
    public Response deleteFriend(@PathVariable("userId")String userId,
                                 @PathVariable("friendId")String friendId) {
        boolean flag = friendService.deleteFriend(userId, friendId);
        if (flag) {
            return Response.ok().message("删除成功");
        } else {
            return Response.error().message("删除失败");
        }
    }
}

