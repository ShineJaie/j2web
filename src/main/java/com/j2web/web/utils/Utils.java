package com.j2web.web.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 输出由 encrypted 加密后的密码
 * Created by wxj on 16-7-12.
 */
public class Utils {

    public static void main(String args[]) throws Exception {
        String cryptedPassword = new BCryptPasswordEncoder().encode("123");
        System.out.println(cryptedPassword);
    }
}