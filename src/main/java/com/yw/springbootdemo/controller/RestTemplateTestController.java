package com.yw.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * @author yangwei
 * @date 2019/5/17 14:44
 */
@RestController
@RequestMapping(value = "/rest")
public class RestTemplateTestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/getForEntity")
    public void get() {
        ResponseEntity responseEntity = restTemplate
                .getForEntity("http://localhost:9090/user/list", Object.class);
        HttpHeaders headers = responseEntity.getHeaders();
        HttpStatus status = responseEntity.getStatusCode();
    }
}
