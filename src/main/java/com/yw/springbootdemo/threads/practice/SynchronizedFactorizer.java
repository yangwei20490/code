package com.yw.springbootdemo.threads.practice;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author yangwei
 * @date 2020-06-08 10:11
 * 线程安全，性能有问题
 */
public class SynchronizedFactorizer implements Servlet {

    private BigInteger lastNumber;
    private BigInteger[] lastFactors;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public synchronized void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
