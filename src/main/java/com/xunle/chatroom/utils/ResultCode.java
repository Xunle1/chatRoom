package com.xunle.chatroom.utils;

/**
 * @author xunle
 */
public enum ResultCode {
    //自定义系列
    SUCCESS(20000, "成功"),
    ERROR(20001,"出现异常"),
    USERNAME_EXISTS(20002,"用户名已存在"),
    USERNAME_OR_PASSWORD_ERROR(20003, "用户名或密码错误"),
    FRIEND_EXISTS(20004, "好友已存在"),
    USERNAME_NOT_EXISTS(20005,"不存在该用户"),
    ROOM_EXISTS(20006,"该群组已存在"),
    ENTER_ERROR(20007,"已经加入该群组"),

    //400系列
    BAD_REQUEST(400,"请求的数据格式不符!"),
    UNAUTHORIZED(401,"登录凭证过期!"),
    FORBIDDEN(403,"抱歉，你无权限访问!"),
    NOT_FOUND(404, "请求的资源找不到!"),

    //500系列
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVICE_UNAVAILABLE(503,"服务器正忙，请稍后再试!"),

    //未知异常
    UNKNOWN(10000,"未知异常!");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
