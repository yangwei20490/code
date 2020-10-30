package com.yw.springbootdemo.queue;

/**
 * @author yangwei
 * @date 2020-05-05 17:27
 */
public class TestServiceHandler implements QueueTaskHandler {

    // ******* start 这一段并不是必要的，这是示范一个传值的方式
    private String name;

    private Integer age;

    public TestServiceHandler(String name) {
        this.name = name;
    }

    public TestServiceHandler(Integer age) {
        this.age = age;
    }

    public TestServiceHandler(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // ****** end

    // 这里也就是我们实现QueueTaskHandler的处理接口
    @Override
    public void processData() {
        // 可以去做你想做的业务了
        // 这里需要引用spring的service的话，我写了一个工具类，下面会贴出来
        // ItestService testService = SpringUtils.getBean(ItestService.class);
        System.out.println("name > " + name + "," + "age > " + age);
    }
}
