package com.j2web.web.test.staticcode;

/**
 * 静态代码块
 * Created by wxj on 16-7-21.
 */
public class ShinejaieDriver {

    static {
        System.out.println("这是静态代码块");

        ShinejaieManager.name = "ShineJaie";
    }

    public ShinejaieDriver() {
        System.out.println("这是构造函数");
    }

}
