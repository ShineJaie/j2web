package com.j2web.web.test.db;

import com.j2web.web.db.master.mapper.UserMapper;
import com.j2web.web.service.master.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 数据库操作测试类
 * Created by wxj on 16-7-25.
 */
@Controller
public class DbController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/test/db", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void testDb(HttpServletResponse response) throws IOException {

        response.setHeader("Content-Type", "text/html;charset=utf-8");

        /*Users user = new Users();
        user.setId(6);
        List<Users> getUsers = userMapper.selectById(user);
        for (Users getUser : getUsers) {
            response.getWriter().write(getUser.getUsername());
            response.getWriter().write("<hr/>");
        }*/

        /*Users user = new Users();
        user.setCreatedate(Calendar.getInstance().getTime());
        user.setUsername("abc");
        user.setPassword("123");
        user.setEmail("email");
        user.setSex(2);
        user.setEnabled(1);
        int res = userMapper.insertUser(user);
        response.getWriter().write(String.valueOf(res) + ", id: " + user.getId());*/

        /*List<Users> list = new ArrayList<>();

        Users user1 = new Users();
        user1.setCreatedate(Calendar.getInstance().getTime());
        user1.setUsername("abc");
        user1.setPassword("123");
        user1.setEmail("email");
        user1.setSex(2);
        user1.setEnabled(1);
        list.add(user1);

        Users user2 = new Users();
        user2.setCreatedate(Calendar.getInstance().getTime());
        user2.setUsername("abc2");
        user2.setPassword("123");
        user2.setEmail("email2");
        user2.setSex(2);
        user2.setEnabled(1);
        list.add(user2);

        int res = userMapper.insertUsers(list);
        response.getWriter().write(String.valueOf(res));*/

        /*Users user3 = new Users();
        user3.setId(3);

        Users user4 = new Users();
        user4.setId(4);

        Users user5 = new Users();
        user5.setId(5);

        int res = userMapper.updateMulti(new ArrayList<>(Arrays.asList(user3, user4, user5)));
        response.getWriter().write(String.valueOf(res));*/

        try {
            userService.addUser("kitty", 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
