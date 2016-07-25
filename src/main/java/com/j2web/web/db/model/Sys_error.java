package com.j2web.web.db.model;

import java.util.Date;

/**
 * 系统错误日志表
 * Created by wxj on 16-7-25.
 */
public class Sys_error {

    private int id; // 自增长 id
    private Date createdate; // 创建日期
    private Date modifydate; // 修改日期
    private String operator; // 操作人
    private String method; // 方法名
    private String args; // 参数值数组
    private String error; // 错误信息

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
