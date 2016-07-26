package com.j2web.web.service.master;

import com.j2web.web.db.master.mapper.SysErrorMapper;
import com.j2web.web.db.master.model.Sys_error;
import com.j2web.web.utils.MyWebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * 系统错误日志相关操作
 * Created by wxj on 16-7-25.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysErrorService {

    @Autowired
    SysErrorMapper sysErrorMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addLog(String method, String args, String error) {

        Sys_error data = new Sys_error();
        data.setMethod(method);
        data.setArgs(args);
        data.setError(error);
        data.setCreatedate(Calendar.getInstance().getTime());
        data.setOperator(MyWebUtils.getCurrentUsername().isEmpty() ?
                "-No Username-" : MyWebUtils.getCurrentUsername());

        sysErrorMapper.insertSysError(data);
    }

}
