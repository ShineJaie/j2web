package com.j2web.web.test.xml2aop;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AOP 实现记录用户日志
 * Created by wxj on 16-7-22.
 */
public class CommonTool {

    private HttpServletResponse response;

    public void addUserLog() throws IOException {
        // 记录用户日志
        response.getWriter().write("记录用户日志成功");
        response.getWriter().write("<hr/>");
    }

    public void addUserOnline(boolean result) throws IOException {
        // 记录在线人数
        if (result) {
            response.getWriter().write("记录在线人数成功");
        } else {
            response.getWriter().write("登录失败，不记录用户在线人数");
        }
        response.getWriter().write("<hr/>");
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

}
