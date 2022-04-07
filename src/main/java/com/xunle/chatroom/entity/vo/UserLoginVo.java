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
public class UserLoginVo {
    private String username;
    private String password;
    private String email;
}
