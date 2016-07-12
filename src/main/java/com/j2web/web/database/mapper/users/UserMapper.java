package com.j2web.web.database.mapper.users;


import com.j2web.web.database.model.Users;

/**
 * 用户相关的 sql
 * Created by admin on 2016/3/13.
 */
public interface UserMapper {

    int countUser(Users user);

    int addUser(Users user);
}
