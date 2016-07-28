package com.j2web.web.test.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟数据库连接池
 * Created by wxj on 16-7-28.
 */
public class ShineJaiePool {

    //    public static Connection pool = null;
    public static Map<Connection, String> pools = new HashMap<>();
    public static String connUrl = "jdbc:mysql://localhost:3306/j2web?user=root&password=root&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            pool = DriverManager.getConnection(connUrl);
            for (int i = 0; i < 2; ++i) { // 默认连接池初始有两个连接
                pools.put(DriverManager.getConnection(connUrl), "0");
                // 0代表空闲；1代表正在交互
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {

        Connection conn = null;

        // 遍例 map 的推荐写法，尤其容量大时效率会高些
        System.out.println("通过Map.entrySet遍历key和value");
        System.out.println(pools.toString());
        for (Map.Entry<Connection, String> entry : pools.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());

            if (entry.getValue().equals("0")) {
                conn = entry.getKey();
                entry.setValue("1");
                break;
            }
        }
        if (conn == null) {
            conn = DriverManager.getConnection(connUrl);
            pools.put(conn, "1");
        }
        return conn;
    }

}
