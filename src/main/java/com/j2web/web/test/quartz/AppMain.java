package com.j2web.web.test.quartz;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Quartz 与 Spring 整合结果测试
 * Created by admin on 2016/4/20.
 */
public class AppMain {

    public static void main(String args[]) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:/config/spring/quartz-context.xml");
    }

}
