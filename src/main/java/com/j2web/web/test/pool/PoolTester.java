package com.j2web.web.test.pool;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟的数据库连接池测试类
 * Created by wxj on 16-7-28.
 */
@RestController
public class PoolTester {

    @RequestMapping(value = "/test/pool", method = RequestMethod.GET)
    public Map<String, Object> pool() throws ClassNotFoundException, SQLException {

        /*// 未采用连接池
        String connUrl = "jdbc:mysql://localhost:3306/j2web?user=root&password=root&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(connUrl);
        conn.createStatement().execute("SELECT sleep(10)");
        conn.close();*/

        Class.forName("com.j2web.web.test.pool.ShineJaiePool");
//        ShineJaiePool.pool.createStatement().execute("SELECT sleep(10)");
        Connection conn = ShineJaiePool.getConnection();
        conn.createStatement().execute("SELECT sleep(10)");
        ShineJaiePool.pools.put(conn, "0");

        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("data", "Successful operation");
        return res;
    }

}
