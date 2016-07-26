package com.j2web.web.db.master.mapper;

import com.j2web.web.db.master.model.Sys_error;
import org.springframework.stereotype.Component;

/**
 * 系统错误日志的相关数据库操作
 * Created by wxj on 16-7-25.
 */
@Component
public interface SysErrorMapper {

    int insertSysError(Sys_error sys_error);

}
