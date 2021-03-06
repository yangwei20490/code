package com.yw.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangwei
 * @date 2019/5/30 15:02
 */
@RestController
public class MiyaController {

    @Value("${my.name}")
    private String name;

    @Value("${my.age}")
    private int age;

    @GetMapping(value = "/miya")
    public String miya() {
        return "name: ".concat(name);
    }
}
