package com.yw.springbootdemo.shiro;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yangwei
 * @date 2019/5/6 16:46
 */
public class RoleTest extends BaseTest {

    @Test
    public void testHasRole() {
        login("classpath:shiro-role.ini", "zhang", "123");
        //判断拥有角色：role1
        Assert.assertTrue(subject().hasRole("role1"));
    }
}
