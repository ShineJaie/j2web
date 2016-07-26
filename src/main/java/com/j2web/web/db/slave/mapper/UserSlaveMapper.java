package com.j2web.web.db.slave.mapper;

import com.j2web.web.db.slave.model.UsersSlave;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 从数据库的用户数据库操作
 * Created by wxj on 2016/7/27.
 */
@Component
public interface UserSlaveMapper {

    List<UsersSlave> selectAll();

    int insertUser(UsersSlave user);

}
