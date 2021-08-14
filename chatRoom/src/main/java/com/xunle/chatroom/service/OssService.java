package com.xunle.chatroom.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xunle
 */
public interface OssService {
    /**
     * 上传文件
     * @param file 上传的文件
     * @return 返回文件url
     */
    String upload(MultipartFile file);
}
