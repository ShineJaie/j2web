package com.j2web.web.db.model;

import java.util.Date;

/**
 * 用户表
 */
public class Users {

    private int id; // 自增长 id
    private Date createdate; // 创建日期
    private Date modifydate; // 修改日期
    private String username; // 用户名
    private String password; // 密码
    private String email; // 邮箱
    private int sex; // 性别, 1: 男, 2: 女
    private int enabled; // 是否启用, 1: 启用, 0: 不启用

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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
