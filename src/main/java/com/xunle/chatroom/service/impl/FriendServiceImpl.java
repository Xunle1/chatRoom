package com.xunle.chatroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunle.chatroom.entity.Friend;
import com.xunle.chatroom.entity.FriendReq;
import com.xunle.chatroom.entity.User;
import com.xunle.chatroom.entity.vo.FriendVO;
import com.xunle.chatroom.handler.ServiceException;
import com.xunle.chatroom.mapper.FriendMapper;
import com.xunle.chatroom.service.FriendReqService;
import com.xunle.chatroom.service.FriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunle.chatroom.service.UserService;
import com.xunle.chatroom.common.ResultCode;
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
 * @since 2021-07-30
 */
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendReqService reqService;

    @Override
    public boolean sendAddRequest(FriendReq friendReq) {
        //判断是否存在该用户
        QueryWrapper<User> friendWrapper = new QueryWrapper<>();
        friendWrapper.eq("username",friendReq.getFriendReqUsername());
        User friend = userService.getOne(friendWrapper);
        if (null == friend) {
            throw new ServiceException(ResultCode.USERNAME_NOT_EXISTS.getCode(),
                    ResultCode.USERNAME_NOT_EXISTS.getMessage());
        }
        friendReq.setFriendReqId(friend.getId());

        //判断该用户是否已经在好友列表
        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.eq("friend_id", friend.getId());
        wrapper.eq("user_id", friendReq.getUserReqId());
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new ServiceException(ResultCode.FRIEND_EXISTS.getCode(),
                    ResultCode.FRIEND_EXISTS.getMessage());
        }

        return reqService.sendReq(friendReq);
    }

    @Override
    public List<FriendVO> getFriends(String userId) {
        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Friend> friends = baseMapper.selectList(wrapper);

        List<FriendVO> friendVOList = new ArrayList<>();
        for (Friend friend : friends) {
            User user = userService.getById(friend.getFriendId());
            FriendVO friendVO = new FriendVO();
            friendVO.setFriendId(user.getId());
            friendVO.setFriendName(user.getUsername());
            friendVO.setAvatar(user.getAvatar());
            friendVO.setSign(user.getSign());

            friendVOList.add(friendVO);
        }

        return friendVOList;
    }

    @Override
    public void addFriend(FriendReq req) {
        String userReqId = req.getUserReqId();
        String friendReqId = req.getFriendReqId();

        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.eq("friend_id", friendReqId);
        wrapper.eq("user_id", userReqId);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new ServiceException(ResultCode.FRIEND_EXISTS.getCode(),
                    ResultCode.FRIEND_EXISTS.getMessage());
        }

        User reqUser = userService.getById(userReqId);
        User reqFriend = userService.getById(friendReqId);

        Friend friend = new Friend();
        friend.setUserId(userReqId);
        friend.setFriendId(friendReqId);
        friend.setFriendUsername(reqFriend.getUsername());
        baseMapper.insert(friend);

        //两方都要添加
        Friend friend1 = new Friend();
        friend1.setUserId(friendReqId);
        friend1.setFriendId(userReqId);
        friend1.setFriendUsername(reqUser.getUsername());
        baseMapper.insert(friend1);
    }

    @Override
    public boolean deleteFriend(String userId, String friendId) {
        QueryWrapper<Friend> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("user_id",userId);
        wrapper1.eq("friend_id",friendId);
        baseMapper.delete(wrapper1);

        QueryWrapper<Friend> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("friend_id", userId);
        wrapper2.eq("user_id", friendId);
        baseMapper.delete(wrapper2);

        return true;
    }
}
