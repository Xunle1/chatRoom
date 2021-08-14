package com.xunle.chatroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunle.chatroom.entity.FriendReq;
import com.xunle.chatroom.entity.User;
import com.xunle.chatroom.entity.vo.FriendReqVO;
import com.xunle.chatroom.mapper.FriendReqMapper;
import com.xunle.chatroom.service.FriendReqService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunle.chatroom.service.FriendService;
import com.xunle.chatroom.service.UserService;
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
public class FriendReqServiceImpl extends ServiceImpl<FriendReqMapper, FriendReq> implements FriendReqService {
    @Autowired
    private FriendService friendService;
    @Autowired
    private UserService userService;

    @Override
    public boolean sendReq(FriendReq req) {
        if (req != null) {
            baseMapper.insert(req);
            return true;
        }
        return false;
    }

    @Override
    public List<FriendReqVO> getRequests(String userId) {
        QueryWrapper<FriendReq> wrapper = new QueryWrapper<>();
        //该用户收到的请求，req的friendReqId == userId
        wrapper.eq("friend_req_id",userId);
        wrapper.orderByDesc("gmt_create");
        List<FriendReq> reqs = baseMapper.selectList(wrapper);

        //封装请求前端展示
        List<FriendReqVO> reqVOList = new ArrayList<>();
        for (FriendReq req : reqs) {
            User requestUser = userService.getById(req.getUserReqId());
            FriendReqVO reqVO = new FriendReqVO();
            reqVO.setId(req.getId());
            reqVO.setFriendReqId(requestUser.getId());
            reqVO.setReqAvatar(requestUser.getAvatar());
            reqVO.setReqUsername(requestUser.getUsername());
            reqVO.setReqMessage(req.getReqMessage());
            reqVO.setSendTime(req.getGmtCreate());

            reqVOList.add(reqVO);
        }
        return reqVOList;
    }

    @Override
    public boolean handleRequest(FriendReq req) {
        if (req.getAccept() == 1) {
            friendService.addFriend(req);
            req.setAccept(1);
            System.out.println("req =======> " + req);
            baseMapper.updateById(req);
            baseMapper.deleteById(req);
            return true;
        }

        req.setAccept(0);
        baseMapper.updateById(req);
        baseMapper.deleteById(req);
        return false;
    }
}
