package com.j2web.web.test.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 集成方式: JobDetailFactoryBean, 并且任务类需要继承 QuartzJobBean
 * Created by admin on 2016/4/20.
 */
public class ScheduledJob extends QuartzJobBean {

    private AnotherBean anotherBean;

    private MyBean myBean;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        // anotherBean.printAnotherMessage();

        myBean.printMessage();
    }

    public void setAnotherBean(AnotherBean anotherBean) {
        this.anotherBean = anotherBean;
    }

    public void setMyBean(MyBean myBean) {
        this.myBean = myBean;
    }
}
