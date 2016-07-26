package com.j2web.web.service.slave;

import com.j2web.web.db.master.model.Users;
import com.j2web.web.db.slave.mapper.UserSlaveMapper;
import com.j2web.web.db.slave.model.UsersSlave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 从数据库的用户操作逻辑
 * Created by wxj on 2016/7/27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserSlaveService {

    @Autowired
    UserSlaveMapper userSlaveMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<UsersSlave> getAllUsers() {
        return userSlaveMapper.selectAll();
    }

//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UsersSlave addUser(String username, String remark) throws Exception {

        UsersSlave user = new UsersSlave();
        user.setUsername(username);
        user.setRemark(remark);

        userSlaveMapper.insertUser(user);

//        int num = 10 / 0;
//        throw new RuntimeException("手动抛出运行时异常");
        throw new Exception("手工 checked 异常");
//        return user;
    }

}
