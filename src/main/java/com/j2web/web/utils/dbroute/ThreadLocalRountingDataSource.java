package com.j2web.web.utils.dbroute;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 实现对不同数据源的路由功能
 * Created by wxj on 16-7-26.
 */
public class ThreadLocalRountingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceTypeManager.get();
    }
}
