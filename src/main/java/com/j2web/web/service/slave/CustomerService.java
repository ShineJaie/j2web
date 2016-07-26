package com.j2web.web.service.slave;

import com.j2web.web.db.slave.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 从数据库客户表操作
 * Created by wxj on 16-7-26.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Map<String, Object>> showAll() {
        return customerMapper.selectAll();
    }

}
