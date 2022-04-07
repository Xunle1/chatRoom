package com.xunle.chatroom.service;

import com.xunle.chatroom.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunle.chatroom.entity.vo.UserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xunle
 * @since 2021-08-05
 */
public interface RoomService extends IService<Room> {

    List<UserVO> getUsers(String roomId);

    List<Room> getRoomList(String userId);

    boolean createRoom(String name);

    boolean enterRoom(String name, String userId);

    boolean leaveRoom(String roomId, String userId);
}
