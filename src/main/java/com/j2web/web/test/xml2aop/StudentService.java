package com.j2web.web.test.xml2aop;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AOP 测试学生用户逻辑类
 * Created by wxj on 16-7-22.
 */
public class StudentService implements UserService {

    private HttpServletResponse response;

    @Override
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void userReg() throws IOException {
        response.getWriter().write("学生注册成功");
        response.getWriter().write("<hr/>");
    }

    @Override
    public boolean userLogin() throws IOException {
        response.getWriter().write("学生登录失败");
        response.getWriter().write("<hr/>");
        return false;
    }
}
