package com.j2web.web.service;

import com.j2web.web.db.mapper.UserMapper;
import com.j2web.web.db.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户相关操作
 * Created by wxj on 16-7-25.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 事务处理测试
     */
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addUser(String username, Integer sex) throws Exception {

        Users user = new Users();
        user.setUsername(username);
        user.setSex(sex);

        userMapper.insertUser(user);
//        int num = 10 / 0;
//        throw new RuntimeException("手动抛出运行时异常");
//        throw new Exception("手工 checked 异常");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Users getUser(Integer userId) {
        Users user = new Users();
        user.setId(userId);
        return userMapper.selectById(user).get(0);
    }

}
