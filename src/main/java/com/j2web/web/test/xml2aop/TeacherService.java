package com.j2web.web.test.xml2aop;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AOP 测试教师用户逻辑类
 * Created by wxj on 16-7-22.
 */
public class TeacherService implements UserService {

    private HttpServletResponse response;

    @Override
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void userReg() {

    }

    @Override
    public boolean userLogin() throws IOException {
        response.getWriter().write("教师登录成功");
        return true;
    }
}
