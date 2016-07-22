package com.j2web.web.test.xml2aop;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AOP 测试 controller
 * Created by wxj on 16-7-22.
 */
@Controller
public class AopLoginController {

    @RequestMapping(value = "/test/xml2aop")
    public void showMsg(HttpServletResponse response) throws IOException {

        response.setHeader("Content-Type", "text/html;charset=utf-8");

        /*UserService studentService = new StudentService();
        UserService teacherService = new TeacherService();

        studentService.setResponse(response);
        studentService.userLogin();

        response.getWriter().write("<hr/>");

        teacherService.setResponse(response);
        teacherService.userLogin();*/

        BeanFactory factory = new ClassPathXmlApplicationContext("config/test/aop-config.xml");
        UserService studentService = (UserService) factory.getBean("studentService");
        CommonTool commonTool = (CommonTool) factory.getBean("commonTool");
        commonTool.setResponse(response);
        studentService.setResponse(response);
        studentService.userLogin(); // 执行登录
//        studentService.userReg(); //执行注册

    }

}
