package com.j2web.web.db.mapper;


import com.j2web.web.db.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户相关的 sql
 * Created by admin on 2016/3/13.
 */
@Component
public interface UserMapper {

    List<Users> selectAll();

    List<Users> selectById(Users user);

    int insertUser(Users user);

    int insertUsers(List<Users> list);

    int updateMulti(List<Users> list);

}
