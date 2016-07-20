package com.j2web.web.test.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 使用反射获取注解和方法
 * Created by wxj on 16-7-19.
 */
public class ReflectTester {

    public static void main(String[] args) {

        Class clazz = null;
        try {
            // 获取方法并调用
            clazz = Class.forName("com.j2web.web.test.annotation.MyAnnotationUser");
            Object object = clazz.newInstance();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                System.out.println(method.getName());
            }
            Method method = clazz.getMethod("getName", String.class);
            System.out.println(method.invoke(object, "吴仙杰"));

            // 获取注解
//            Annotation[] annotations = clazz.getAnnotations(); // 获取类上的注解
//            Annotation[] annotations = method.getAnnotations(); // 获取方法上的注解

//            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class); // 获取方法上的 MyAnnotation 注解
//            System.out.println(annotation.name());

            Field[] fields = clazz.getDeclaredFields(); // 获取类自已经申明的属性， 不包括父类
            for (Field field : fields) {
                Annotation[] annotations = field.getAnnotations(); // 获取属性上的所有注解
                for (Annotation annotation : annotations) {
                    if (MyAnnotation.class.isInstance(annotation)) { // 判断 annotation 是否是 MyAnnotation 的实例
                        // 获取申明类的名称 如 class com.j2web.web.utils.WebAppStartInit
                        String getClassName = field.getGenericType().toString();
                        System.out.println(getClassName);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
