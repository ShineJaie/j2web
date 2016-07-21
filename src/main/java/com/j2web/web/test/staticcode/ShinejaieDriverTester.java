package com.j2web.web.test.staticcode;

/**
 * 静态代码块测试类
 * Created by wxj on 16-7-21.
 */
public class ShinejaieDriverTester {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.j2web.web.test.staticcode.ShinejaieDriver");
//        ShinejaieDriver shinejaieDriver1 = new ShinejaieDriver();
//        ShinejaieDriver shinejaieDriver2 = new ShinejaieDriver();

        ShinejaieManager.showName();
    }

}
