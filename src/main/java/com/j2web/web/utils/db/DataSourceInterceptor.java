package com.j2web.web.utils.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 指定 dataSource 的 key，从而使 dataSource 根据 key 选择相应的数据源
 * Created by wxj on 16-7-26.
 */
@Aspect // for aop
@Component // for auto scan
@Order(0)  // execute before @Transactional
public class DataSourceInterceptor {

    @Pointcut("execution(* com.j2web.web.service.slave..*.*(..))")
    public void dataSourceSlave() {
    }

    @Before("dataSourceSlave()")
    public void logBefore(JoinPoint joinPoint) {
        DataSourceTypeManager.set(DataSources.SLAVE);
    }

}
