package com.xunle.chatroom.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xunle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendVO {
    String friendId;
    String friendName;
    String avatar;
    String sign;
}
