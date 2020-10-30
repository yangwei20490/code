package com.yw.springbootdemo.design.strategy;

/**
 * @author yangwei
 * @date 2019/7/22 11:26
 */
public class ConcreteStrategyA extends Strategy {

    @Override
    public void AlgorithmInterface() {
        System.out.println("算法A实现");
    }
}
