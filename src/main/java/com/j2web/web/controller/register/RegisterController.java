package com.j2web.web.controller.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户注册
 * Created by wxj on 16-7-13.
 */
@Controller
public class RegisterController {

    @RequestMapping(value = "/register")
    public String logoutPage() {

        return "register";
    }

}
