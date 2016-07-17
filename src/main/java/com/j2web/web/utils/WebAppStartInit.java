package com.j2web.web.utils;

import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 项目初始化类
 * Created by wxj on 16-7-12.
 */
public class WebAppStartInit implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(WebAppStartInit.class);

    /**
     * 网站上线
     *
     * @param servletContextEvent ServletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        // 获取网站根路径
        MyFileUtils.rootPath = servletContextEvent.getServletContext().getRealPath("/");

        String msg = "网站正在启动中...";
        logger.info(msg);

        // 初始化 jedis 连接池
        String ip = "localhost";
        MyWebUtils.jedisPool = new JedisPool(new JedisPoolConfig(), ip);
        logger.info("初始化 jedis 连接池");

    }

    /**
     * 网站下线
     *
     * @param servletContextEvent ServletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        String msg = "网站下线中...";
        logger.info(msg);

        MyWebUtils.jedisPool.destroy();
        logger.info("销毁 jedis 连接池");
    }
}
