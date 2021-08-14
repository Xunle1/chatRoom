package com.xunle.chatroom.service;

import com.xunle.chatroom.entity.Friend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunle.chatroom.entity.FriendReq;
import com.xunle.chatroom.entity.User;
import com.xunle.chatroom.entity.vo.FriendVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xunle
 * @since 2021-07-30
 */
public interface FriendService extends IService<Friend> {

    boolean sendAddRequest(FriendReq friendReq);

    List<FriendVO> getFriends(String userId);

    void addFriend(FriendReq req);

    boolean deleteFriend(String userId, String friendId);
}
