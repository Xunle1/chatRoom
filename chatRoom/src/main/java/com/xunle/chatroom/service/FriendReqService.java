package com.xunle.chatroom.service;

import com.xunle.chatroom.entity.FriendReq;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunle.chatroom.entity.vo.FriendReqVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xunle
 * @since 2021-07-30
 */
public interface FriendReqService extends IService<FriendReq> {

    boolean sendReq(FriendReq req);

    List<FriendReqVO> getRequests(String userId);

    boolean handleRequest(FriendReq req);
}
