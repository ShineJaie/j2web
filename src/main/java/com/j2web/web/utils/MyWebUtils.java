package com.j2web.web.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 工具类 网站展示相关处理
 */
@Component
public class MyWebUtils implements ApplicationContextAware {

    private static Logger logger = Logger.getLogger(MyWebUtils.class);

    /**
     * Spring 上下文
     */
    public static ApplicationContext APPLICATION_CONTEXT = null;

    /**
     * 单个的 jedis 实例不是线程安全,<br/>
     * 使用 JedisPool, 这是一个线程安全的网络连接池, 可以将其存贮为一个静态的.<br/>
     * 当 jedis 无法从连接池获取连接时, 它将会断开并且关闭 jedis.
     */
    public static JedisPool JEDIS_POOL = null;

    public ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        APPLICATION_CONTEXT = applicationContext;
    }

    /**
     * 获取请求的服务器信息
     *
     * @param request HttpServletRequest
     * @return 1, serverName-服务器名称(根据客服端的请求地址变化,
     * localhost, 127.0.0.1, 10.0.0.108, 域名等)<br/>
     * 2, serverPort-服务器端口号<br/>
     * 3, appName-项目名<br/>
     * 4, serverRootUri-请求项目根路径uri
     */
    public static Map<String, Object> getServerInfo(HttpServletRequest request) {

        /*--------------------------------------------------------------*/

        // Example: http://myhost:8080/people?lastname=Fox&age=30

        /*String uri = request.getScheme() + "://" +   // "http" + "://
                request.getServerName() +       // "myhost"
                ":" +                           // ":"
                request.getServerPort() +       // "8080"
                request.getRequestURI() +       // "/people"
                "?" +                           // "?"
                request.getQueryString();       // "lastname=Fox&age=30"*/

        /*--------------------------------------------------------------*/

        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String appName = request.getContextPath();

        // Example: http://localhost:8080/WebPro
        String serverRootUri = request.getScheme() + "://" +
                serverName + ":" +
                serverPort +
                appName;

        Map<String, Object> res = new HashMap<String, Object>();

        res.put("serverName", serverName);
        res.put("serverPort", serverPort);
        res.put("appName", appName);
        res.put("serverRootUri", serverRootUri);

        return res;
    }

    /**
     * 从 SecurityContext 中获取当前登录用户的用户名
     *
     * @return username
     */
    public static String getCurrentUsername() {
        String username = null;

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        }
        return username;
    }

    /**
     * 对比解密是否相同
     *
     * @param rawPass     未加密的密码
     * @param encodedPass 已加密的密码
     * @return true: 密码相同, false: 密码不同
     */
    public static boolean comparePassword(String rawPass, String encodedPass) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPass, encodedPass);
    }

    /**
     * 为 sql 语句生成模糊查询的变量值
     *
     * @param str 原始字符串
     * @return 模糊搜索的字符串, 若传入 空 或 null 字符串则返回空字符串
     */
    public static String generFuzzyParam(String str) {

        str = dealWithNullVal(str);

        if (str.isEmpty()) {
            return str;
        }

        StringBuffer strBuf = new StringBuffer(str);
        strBuf.append("%").insert(0, "%").trimToSize();

        return strBuf.toString();
    }

    /**
     * 处理 null 字符串
     *
     * @param str 字符串
     * @return null 输出"", 否则输出 .trim()
     */
    public static String dealWithNullVal(String str) {
        if (str == null) {
            return "";
        } else {
            return str.trim();
        }
    }

    /**
     * 处理 null 数字
     *
     * @param input 数字 int
     * @return null 返回 0
     */
    public static Integer dealWithNullVal(Integer input) {
        if (input == null) {
            return 0;
        } else {
            return input;
        }
    }

    /**
     * 处理 null 数字
     *
     * @param input 数字 double
     * @return null 返回 0d
     */
    public static Double dealWithNullVal(Double input) {
        if (input == null || input.isInfinite() || input.isNaN()) {
            return 0d;
        } else {
            return input;
        }
    }

    /**
     * 程序返回结果转换成json字符串, 给提示框使用
     *
     * @param status 状态, 0:error, 1:success
     * @param data   显示内容
     * @return {"error/success", "data"}
     */
    public static String convert2JsonResult(Integer status, String data) {
        JSONObject res = new JSONObject();

        if (0 == status) {
            res.put("status", "error");
        } else if (1 == status) {
            res.put("status", "success");
        }

        res.put("data", data);
        return res.toString();
    }

}
