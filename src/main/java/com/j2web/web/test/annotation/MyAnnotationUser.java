package com.j2web.web.test.annotation;

/**
 * 使用注解的类, 提供反射方式获取方法和注解的测试类
 * Created by wxj on 16-7-19.
 */
@MyAnnotation(name = "su", age = 21)
public class MyAnnotationUser {

    @MyAnnotation(name = "shinejaie", age = 19)
    public String getName(String name) {
        return name;
    }

    public Integer getAge() {
        return 18;
    }

}
