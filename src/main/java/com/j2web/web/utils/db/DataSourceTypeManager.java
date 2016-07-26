package com.j2web.web.utils.db;

/**
 * 通过 TheadLocal 来保存每个线程选择哪个数据源的标志(key)
 * Created by wxj on 16-7-26.
 */
public class DataSourceTypeManager {

    private static final ThreadLocal<DataSources> dataSourceTypes = new ThreadLocal<DataSources>() {
        @Override
        protected DataSources initialValue() {
            return DataSources.MASTER;
        }
    };

    public static DataSources get() {
        return dataSourceTypes.get();
    }

    public static void set(DataSources dataSourceType) {
        dataSourceTypes.set(dataSourceType);
    }

    public static void reset() {
        dataSourceTypes.set(DataSources.MASTER);
    }
}