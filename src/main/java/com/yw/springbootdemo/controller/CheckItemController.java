package com.yw.springbootdemo.controller;

import com.ssi.common.web.ResponseData;
import com.yw.springbootdemo.service.ICheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangwei
 * @date 2019/5/9 15:01
 */
@RestController
@RequestMapping(value = "/checkItem")
public class CheckItemController {

    @Autowired
    private ICheckItemService checkItemService;

    @GetMapping
    public ResponseData test() {
        ResponseData responseData = ResponseData.createResponseData();
        responseData.setData(checkItemService.check());
        return responseData;
    }
}
