package com.j2web.web.test.aop;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerAOP {


    @ResponseBody
    @RequestMapping(value = "/test/aop1", produces = "application/json;charset=UTF-8")
    public void addCustomer() {
        System.out.println("addCutomer() is running");
    }

    @ResponseBody
    @RequestMapping(value = "/test/aop2", produces = "application/json;charset=UTF-8")
    public String addCustomerReturnValue() {
        System.out.println("addCustomerReturnValue() is running");
        return "abc";
    }

    @ResponseBody
    @RequestMapping(value = "/test/aop3", produces = "application/json;charset=UTF-8")
    public void addCustomerThrowException() throws Exception {
        System.out.println("addCustomerThrowException() is running");
        throw new Exception("Generic error");
    }

    @ResponseBody
    @RequestMapping(value = "/test/aop4", produces = "application/json;charset=UTF-8")
    public void addCustomerAround(String name) {
        System.out.println("addCustomerAround() is running, args: " + name);
    }
}
