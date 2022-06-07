package com.xunle.chatroom.handler;

import com.xunle.chatroom.common.ResponseError;
import com.xunle.chatroom.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xunle
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Response handleServiceException(ServiceException e, HttpServletRequest request) {
        log.error("业务异常 -> " + e);
        ResponseError error = new ResponseError();
        error.setCode(e.getCode());
        error.setMessage(e.getMessage());
        error.setRequestUrl(request.getRequestURL().toString());
        error.setException(e.getClass().getName());
        return error;
    }

}
