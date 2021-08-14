package com.xunle.chatroom.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xunle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendReqVO {

    private String id;
    private String reqUsername;
    private String friendReqId;
    private String reqAvatar;
    private String reqMessage;
    private Date sendTime;
}
