package com.j2web.web.database.model;

import java.util.Date;

/**
 * 用户表
 */
public class Users {

    public int id; // 自增长 id
    public Date createdate; // 创建日期
    public Date modifydate; // 修改日期
    public String username; // 用户名
    public String password; // 密码
    public String email; // 邮箱
    public int sex; // 性别, 1: 男, 2: 女
    public int enable; // 是否启用, 1: 启用, 0: 不启用

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}
