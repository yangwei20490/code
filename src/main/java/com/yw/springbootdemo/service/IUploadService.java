package com.yw.springbootdemo.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author yangwei
 * @date 2019/6/4 16:55
 */
public interface IUploadService {

    void importUsers(MultipartFile file);
}
