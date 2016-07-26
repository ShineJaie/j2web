package com.j2web.web.db.slave.mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 从数据库客户操作
 * Created by wxj on 16-7-26.
 */
@Component
public interface CustomerMapper {

    List<Map<String, Object>> selectAll();

}
