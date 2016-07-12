package com.j2web.web.test.quartz;

import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务类
 * Created by admin on 2016/4/20.
 */
@Component("anotherBean")
public class AnotherBean {

    public void printAnotherMessage() {

        Timer timer = new Timer();
        timer.schedule(new Task(), 2000, 10000); // 延迟执行
        System.out.println("---> XXXXXXXXXXX");
    }

    public class Task extends TimerTask {

        @Override
        public void run() {
            System.out.println("---> I am anotherBean called by Quartz jobBean using CronTriggerFactoryBean");
        }
    }

}
