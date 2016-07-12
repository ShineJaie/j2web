package com.j2web.web.database.model;

/**
 * 用户角色表
 * Created by wxj on 16-7-12.
 */
public class User_roles {

    public int id; // 自增长 id
    public int user_id; // users表的id
    public String authority; // 角色

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
