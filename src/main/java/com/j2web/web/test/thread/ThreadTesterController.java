package com.j2web.web.test.thread;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试线程的独立性
 * Created by wxj on 16-7-27.
 */
@RestController
public class ThreadTesterController {

    @RequestMapping(value = "/test/thread", method = RequestMethod.GET)
    public List<Map<String, Object>> TestThread() {

        TestClientImp test = new TestClientImp();
        Thread thread = new Thread(test);
        thread.start();

        Map<String, Object> map = new HashMap<>();
        map.put("status", "success");
        map.put("data", "successful operation");

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);

        return list;
    }

}
