package com.j2web.web.db.slave.model;

/**
 * users 表
 * Created by wxj on 2016/7/27.
 */
public class UsersSlave {

    private int id; // 自增长 id
    private String username; // 用户名
    private String remark; // 备注

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
