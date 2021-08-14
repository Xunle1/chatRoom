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
public class MessageVO {
    private String fromId;
    private String toId;
    private String content;
    private int type;
    private Date sendTime;
}
