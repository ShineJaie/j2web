package com.j2web.web.test.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * IOC 测试类
 * Created by wxj on 16-7-22.
 */
@Controller
public class IocController {

    @RequestMapping(value = "/test/ioc")
    public void showMsg(HttpServletResponse response) throws IOException {

        // 正转控制
        /*UserPay userPay = new UserPay();
        userPay.setResponse(response);

        PayMethod payMethod = new BankPay();
        userPay.setPayMethod(payMethod);

        userPay.pay();*/

        // IOC 容器反转控制
        BeanFactory factory = new ClassPathXmlApplicationContext("config/test/pay-config.xml");
        UserPay userPay = (UserPay) factory.getBean("userPay");
        userPay.setResponse(response);

        userPay.pay();
    }

}
