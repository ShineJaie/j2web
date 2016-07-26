package com.j2web.web.controller.register;

import com.j2web.web.db.master.model.Users;
import com.j2web.web.utils.MyWebUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户注册
 * Created by wxj on 16-7-13.
 */
@RestController
public class RegisterController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String save(Users user) {
        System.out.println(user);
        return MyWebUtils.convert2JsonResult(1, "操作成功");
    }

}
