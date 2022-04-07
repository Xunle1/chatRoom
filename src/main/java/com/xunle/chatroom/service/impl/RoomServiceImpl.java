package com.xunle.chatroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunle.chatroom.entity.Relation;
import com.xunle.chatroom.entity.Room;
import com.xunle.chatroom.entity.User;
import com.xunle.chatroom.entity.vo.UserVO;
import com.xunle.chatroom.handler.ServiceException;
import com.xunle.chatroom.mapper.RoomMapper;
import com.xunle.chatroom.service.RelationService;
import com.xunle.chatroom.service.RoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunle.chatroom.service.UserService;
import com.xunle.chatroom.utils.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xunle
 * @since 2021-08-05
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Autowired
    private RelationService relationService;
    @Autowired
    private UserService userService;

    @Override
    public List<UserVO> getUsers(String roomId) {
        QueryWrapper<Relation> wrapper = new QueryWrapper<>();
        wrapper.eq("room_id",roomId);
        List<Relation> relationList = relationService.list(wrapper);

        List<UserVO> userList = new ArrayList<>();
        for (Relation relation : relationList) {
            String userId = relation.getUserId();
            User user = userService.getById(userId);
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user,userVO);
            userList.add(userVO);
        }

        return userList;
    }

    @Override
    public List<Room> getRoomList(String userId) {
        QueryWrapper<Relation> wrapper = new QueryWrapper<>();
        wrapper.eq("user_Id",userId);
        List<Relation> relationList = relationService.list(wrapper);

        List<Room> roomList = new ArrayList<>();
        for (Relation relation : relationList) {
            String roomId = relation.getRoomId();
            Room room = baseMapper.selectById(roomId);
            roomList.add(room);
        }

        return roomList;
    }

    @Override
    public boolean createRoom(String name) {
        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new ServiceException(ResultCode.ROOM_EXISTS.getCode(), ResultCode.ROOM_EXISTS.getMessage());
        }
        Room room = new Room();
        room.setName(name);
        int insert = baseMapper.insert(room);
        return insert > 0;
    }

    @Override
    public boolean enterRoom(String name, String userId) {
        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        Room room = baseMapper.selectOne(wrapper);
        String roomId = room.getId();

        QueryWrapper<Relation> relationQueryWrapper = new QueryWrapper<>();
        relationQueryWrapper.eq("room_id",roomId).eq("user_id",userId);
        int count = relationService.count(relationQueryWrapper);
        if (count > 0) {
            throw new ServiceException(ResultCode.ENTER_ERROR.getCode(), ResultCode.ENTER_ERROR.getMessage());
        }

        Relation relation = new Relation();
        relation.setRoomId(roomId);
        relation.setUserId(userId);
        return relationService.save(relation);
    }

    @Override
    public boolean leaveRoom(String roomId, String userId) {
        QueryWrapper<Relation> wrapper = new QueryWrapper<>();
        wrapper.eq("room_id",roomId).eq("user_id", userId);
        return relationService.remove(wrapper);
    }
}
