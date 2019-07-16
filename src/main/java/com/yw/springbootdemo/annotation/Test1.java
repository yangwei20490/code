package com.yw.springbootdemo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author yangwei
 * @date 2019/5/16 15:51
 */
@TestAnnotation(id = 1, msg = "hello annotation")
public class Test1 {

    @Check("hi")
    int a;

    @Perform
    public void testMethod() {}

    @SuppressWarnings("depretation")
    public void test2 () {
        System.out.println("***");
    }

    public static void main(String[] args) {
        //注解通过反射获取。首先可以通过 Class 对象的 isAnnotationPresent() 方法判断它是否应用了某个注解
        boolean hasAnnotation = Test1.class.isAnnotationPresent(TestAnnotation.class);
        if (hasAnnotation) {
            //然后通过 getAnnotation() 方法来获取 Annotation 对象。或者是 getAnnotations() 方法。
            //前一种方法返回指定类型的注解，后一种方法返回注解到这个元素上的所有注解。
            TestAnnotation testAnnotation = Test1.class.getAnnotation(TestAnnotation.class);
            System.out.println("id: " + testAnnotation.id());
            System.out.println("msg: " + testAnnotation.msg());
        }

        try {
            Field a = Test1.class.getDeclaredField("a");
            a.setAccessible(true);
            //获取一个成员变量上的注解
            Check check = a.getAnnotation(Check.class);
            if (check != null) {
                System.out.println("check value: " + check.value());
            }

            Method testMethod = Test1.class.getDeclaredMethod("testMethod");
            if (testMethod != null) {
                //获取方法中的注解
                Annotation[] annotations = testMethod.getAnnotations();
                System.out.println(annotations.length);
//                Arrays.asList(annotations).forEach(annotation ->
//                        System.out.println("method testMethod annotation: "
//                                + annotation.annotationType().getTypeName()));
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
