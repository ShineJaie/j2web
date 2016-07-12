package com.j2web.web.test.quartz;

import org.springframework.stereotype.Component;

/**
 * 任务类
 * Created by admin on 2016/4/20.
 */
@Component("myBean")
public class MyBean {

    public void printMessage() {
        System.out.println("---> I am myBean called by MethodInvokingJobDetailFactoryBean using SimpleTriggerFactoryBean");
    }

}
