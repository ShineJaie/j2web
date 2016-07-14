package com.j2web.web.controller.register;

import com.j2web.web.utils.MyWebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping(value = "/register/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String save() {
        return MyWebUtils.convert2JsonResult(1, "操作成功");
    }

}
