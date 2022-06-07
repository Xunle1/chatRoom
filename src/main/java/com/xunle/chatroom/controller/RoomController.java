package com.xunle.chatroom.controller;


import com.xunle.chatroom.entity.Room;
import com.xunle.chatroom.entity.vo.UserVO;
import com.xunle.chatroom.service.RoomService;
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
 * @since 2021-08-05
 */
@Api(tags = "群组管理")
@RestController
@RequestMapping("/chatroom")
@CrossOrigin
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/room/{roomName}")
    @ApiOperation("创建群组")
    public Response createRoom(@PathVariable("roomName") String name) {
        boolean flag = roomService.createRoom(name);
        if (flag) {
            return Response.ok().message("创建群组成功");
        } else {
            return Response.error().message("创建群组失败");
        }
    }

    @PutMapping("/roomenter/{name}/user/{userId}")
    @ApiOperation("加入群组")
    public Response enterRoom(@PathVariable("name")String name, @PathVariable("userId")String userId) {
        boolean flag = roomService.enterRoom(name, userId);
        if (flag) {
            return Response.ok().message("加入群组成功");
        } else {
            return Response.ok().message("加入群组失败");
        }
    }

    @GetMapping("/room/{roomId}/users")
    @ApiOperation("群组用户列表")
    public Response getUsers(@PathVariable("roomId")String roomId) {
        List<UserVO> users = roomService.getUsers(roomId);

        return Response.ok().data("roomUsers", users);
    }

    @GetMapping("/room/{userId}/list")
    @ApiOperation("用户群组列表")
    public Response getRooms(@PathVariable("userId")String userId) {
        List<Room> roomList = roomService.getRoomList(userId);

        return Response.ok().data("rooms", roomList);
    }

    @PutMapping("/roomleave/{roomId}/user/{userId}")
    @ApiOperation("退出群组")
    public Response leaveRoom(@PathVariable("roomId")String roomId, @PathVariable("userId")String userId) {
        boolean flag = roomService.leaveRoom(roomId, userId);
        if (flag) {
            return Response.ok().message("退出群组成功");
        } else {
            return Response.error().message("退出群组失败");
        }
    }
}

