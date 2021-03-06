package com.yw.springbootdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.springbootdemo.model.CheckItem;

import java.util.List;
import java.util.Map;

/**
 * @author yangwei
 * @date 2019/5/9 14:57
 */
public interface ICheckItemService extends IService<CheckItem> {
    List<Map<String, Object>> check();
}
