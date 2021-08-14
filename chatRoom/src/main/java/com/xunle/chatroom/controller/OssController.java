package com.xunle.chatroom.controller;

import com.xunle.chatroom.service.OssService;
import com.xunle.chatroom.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xunle
 */
@RestController
@RequestMapping("/chatroom/oss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping
    public Response upload(MultipartFile file) {
        String url = ossService.upload(file);
        return Response.ok().data("url", url);
    }
}
