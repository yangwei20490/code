package com.yw.springbootdemo.design.strategy;

/**
 * @author yangwei
 * @date 2019/7/22 11:29
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void contestInterface() {
        strategy.AlgorithmInterface();
    }

    public static void main(String[] args) {
        Context context;

        context = new Context(new ConcreteStrategyA());
        context.contestInterface();

        context = new Context(new ConcreteStrategyB());
        context.contestInterface();

        context = new Context(new ConcreteStrategyC());
        context.contestInterface();
    }
}
