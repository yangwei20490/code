package com.yw.springbootdemo.controller;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author yangwei
 * @date 2020-09-11 11:34
 */
@Controller
@RequestMapping(value = "/html")
public class HtmlController {

    @RequestMapping("/test")
    public String test(String dpid) {
        return "field";
    }

    public static void main(String[] args) {
        List<Object> list = Lists.newArrayList();
        for(Object s : list){
            System.out.println("a");
        }
    }
}
