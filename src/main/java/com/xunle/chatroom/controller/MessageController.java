package com.xunle.chatroom.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunle.chatroom.entity.Message;
import com.xunle.chatroom.entity.vo.MessageVO;
import com.xunle.chatroom.service.MessageService;
import com.xunle.chatroom.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xunle
 * @since 2021-08-04
 */
@Api(tags = "消息控制器")
@RestController
@RequestMapping("/chatroom")
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/message/{userId}/{toId}")
    @ApiOperation("获取消息记录")
    public Response getMessage(@PathVariable("userId")String userId, @PathVariable("toId")String friendId) {
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("from_id", userId).eq("to_id",friendId).or().eq("to_id", userId).eq("from_id",friendId);
        List<Message> messageList = messageService.list(wrapper);
        List<MessageVO> messageVOList = new ArrayList<>();
        for (Message message : messageList) {
            MessageVO messageVO = new MessageVO();
            BeanUtils.copyProperties(message,messageVO);
            messageVOList.add(messageVO);
        }
        return Response.ok().data("messageList",messageVOList);
    }

    @GetMapping("/message/room/{roomId}")
    @ApiOperation("获取房间消息记录")
    public Response getRoomMessage(@PathVariable("roomId")String roomId) {
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("to_id",roomId);
        List<Message> messagesList = messageService.list(wrapper);
        List<MessageVO> messageVOList = new ArrayList<>();
        for (Message message : messagesList) {
            MessageVO messageVO = new MessageVO();
            BeanUtils.copyProperties(message,messageVO);
            messageVOList.add(messageVO);
        }

        return Response.ok().data("messageList",messageVOList);
    }
}

