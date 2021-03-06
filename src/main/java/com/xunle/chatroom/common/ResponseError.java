package com.xunle.chatroom.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xunle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError extends Response {

    private String requestUrl;
    private String exception;
}
