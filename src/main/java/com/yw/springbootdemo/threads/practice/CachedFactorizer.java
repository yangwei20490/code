package com.yw.springbootdemo.threads.practice;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author yangwei
 * @date 2020-06-08 10:35
 */
public class CachedFactorizer implements Servlet {

    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    public static void main(String[] args) {
        String a = "";
        String b = "456";
        String str =  a.equals("123") ? "123" : ( b.equals("456") ? "456" : "789");
//            如果a等于123     就给str赋值123 否则 如果b等于456  就给str赋值456  前面两个如果都不成立就赋值789
        System.out.println(str);
    }
}
