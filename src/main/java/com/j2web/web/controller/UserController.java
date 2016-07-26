package com.j2web.web.controller;

import com.j2web.web.db.master.model.Users;
import com.j2web.web.db.slave.model.UsersSlave;
import com.j2web.web.service.master.UserService;
import com.j2web.web.service.slave.UserSlaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * RESTful API 接口
 * Created by wxj on 2016/7/25.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserSlaveService userSlaveService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Users getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/userslave", method = RequestMethod.GET)
    public List<UsersSlave> getAllUsersSlave() {
        return userSlaveService.getAllUsers();
    }

    @RequestMapping(value = "/userslave", method = RequestMethod.POST)
    public UsersSlave addUsersSlave() throws Exception {
        return userSlaveService.addUser("bruce", "测试账号");
    }

    @RequestMapping(value = "/home2", method = RequestMethod.GET)
    public ModelAndView getHome() {
        return new ModelAndView("home");
    }

}
