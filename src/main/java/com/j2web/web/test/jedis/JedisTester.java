package com.j2web.web.test.jedis;

import com.j2web.web.utils.MyWebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Redis 与 Java 整合用例
 * Created by admin on 2016/4/19.
 */
@Controller
public class JedisTester {

    @ResponseBody
    @RequestMapping(value = "/test/jedis", produces = "application/json;charset=UTF-8")
    public String jedisTester() {

        JSONObject res = new JSONObject();

        Map<String, Object> user = new HashMap<>();
        user.put("name", "吴仙杰");
        user.put("age", 25);
        user.put("null", null);

        JedisPool jedisPool = MyWebUtils.jedisPool;

        try (Jedis jedis = jedisPool.getResource()) {

            Pipeline p = jedis.pipelined();

            p.hset("developer", "name", "吴仙杰__shinejaie");
            p.hset("developer", "work", "java");
            p.hset("developer", "web", "js");
            p.hset("developer", "age", "25");
            p.hset("developer", "empty", "");

            p.hset("developer", "hashmap", new JSONObject(user).toString());

            Response<Map<String, String>> developerInfo = p.hgetAll("developer");

            p.sync();

            Map<String, String> map = developerInfo.get();

            res = new JSONObject(map.get("hashmap"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res.toString();

    }

    /**
     * 单个的 jedis 实例不是线程安全,<br/>
     * 使用 JedisPool, 这是一个线程安全的网络连接池, 可以将其存贮为一个静态的.<br/>
     * 当 jedis 无法从连接池获取连接时, 它将会断开并且关闭 jedis.
     */
    public void threadsafe() {

        // 初始化 jedis 连接池
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");

        // jedis 实现了  java.lang.AutoCloseable, 因此 jedis 实例会在最后一条语句后自动自动关闭
        try (Jedis jedis = pool.getResource()) {
            // 采用管道实现多命令操作, 这样在发送命令时无需等待响应, 能够提高速度
            Pipeline p = jedis.pipelined();
            p.set("foo", "bar");
            p.zadd("foo", 1, "barowitch");
            p.zadd("foo", 0, "barinsky");
            p.zadd("foo", 0, "barikoviev");
            Response<String> pipeString = p.get("fool");
            Response<Set<String>> sose = p.zrange("foo", 0, -1);
            p.sync();
            int soseSize = sose.get().size();
            Set<String> setBack = sose.get();

            System.out.println(pipeString);
            System.out.println(soseSize);
            System.out.println(setBack);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 当关闭应用程序时, 销毁 jedis 连接池
        pool.destroy();

    }

    /**
     * 单个的 jedis 实例不是线程安全,<br/>
     * 使用 JedisPool, 这是一个线程安全的网络连接池, 可以将其存贮为一个静态的.<br/>
     * 当 jedis 无法从连接池获取连接时, 它将会断开并且关闭 jedis.
     */
    public void threadsafe2() {

        // 初始化 jedis 连接池
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");

        // If you can't use try-with-resource, you can still enjoy with Jedis.close().
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            /// ... do stuff here ... for example
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);

            System.out.println(foobar);
            System.out.println(sose);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        // 当关闭应用程序时, 销毁 jedis 连接池
        pool.destroy();

    }

    public static void main(String[] args) {

        // 初始化 jedis 连接池
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");

        // jedis 实现了  java.lang.AutoCloseable, 因此 jedis 实例会在最后一条语句后自动自动关闭
        try (Jedis jedis = pool.getResource()) {
            // 采用管道实现多命令操作, 这样在发送命令时无需等待响应, 能够提高速度
            Pipeline p = jedis.pipelined();
            p.set("fool", "bar");
            p.zadd("foo", 1, "barowitch");
            p.zadd("foo", 0, "barinsky");
            p.zadd("foo", 0, "barikoviev");
            Response<String> pipeString = p.get("fool");
            Response<Set<String>> sose = p.zrange("foo", 0, -1);
            p.sync();
            int soseSize = sose.get().size();
            Set<String> setBack = sose.get();

            System.out.println(pipeString);
            System.out.println(pipeString.get());

            System.out.println(sose);
            System.out.println(soseSize);
            System.out.println(setBack);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 当关闭应用程序时, 销毁 jedis 连接池
        pool.destroy();
    }

}
