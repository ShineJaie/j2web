package com.j2web.web.test.xml2aop;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** AOP 测试的用户逻辑类
 * Created by wxj on 16-7-22.
 */
public interface UserService {

    void setResponse(HttpServletResponse response);

    void userReg() throws IOException; // 用户注册
    boolean userLogin() throws IOException; // 用户登录

}
