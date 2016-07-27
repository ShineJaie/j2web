package com.j2web.web.test.thread;

import com.j2web.web.db.master.model.Users;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试线程的独立性
 * Created by wxj on 16-7-27.
 */
@RestController
public class ThreadTesterController {

    @RequestMapping(value = "/test/thread", method = RequestMethod.GET)
    public Users TestThread() throws JSONException {

        TestClientImp test = new TestClientImp();
        Thread thread = new Thread(test);
        thread.start();

        Users user = new Users();
        user.setUsername("名字");
        return user;
    }

}
