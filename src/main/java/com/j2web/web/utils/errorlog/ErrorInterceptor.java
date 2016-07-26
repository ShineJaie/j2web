package com.j2web.web.utils.errorlog;

import com.j2web.web.service.master.SysErrorService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 记录全局异常
 * Created by wxj on 16-7-25.
 */
@Aspect // for aop
@Component // for auto scan
public class ErrorInterceptor {

    @Autowired
    SysErrorService sysErrorService;

    private static final Logger logger = Logger.getLogger(ErrorInterceptor.class);

    @AfterThrowing(
            pointcut = "execution(* com.j2web.web.service..*.*(..)) && " +
                    "!execution(* com.j2web.web.service.master.SysErrorService.*(..)))",
            throwing = "error")
    public void logError(JoinPoint joinPoint, Throwable error) {

        String method = joinPoint.getSignature().toString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();

        logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.error("方法：" + method);
        logger.error("入参：" + args);
        logger.error("错误 : " + error.toString());
        logger.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        sysErrorService.addLog(method, args, error.toString());
    }

}
