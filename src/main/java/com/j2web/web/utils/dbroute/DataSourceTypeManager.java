package com.j2web.web.utils.dbroute;

/**
 * 通过 TheadLocal 来保存每个线程选择哪个数据源的标志(key)
 * Created by wxj on 16-7-26.
 */
public class DataSourceTypeManager {

    /**
     * 通过匿名内部类覆盖 ThreadLocal 的 initialValue() 方法，指定初始值
     */
    private static final ThreadLocal<DataSources> dataSourceTypes = new ThreadLocal<DataSources>() {
        @Override
        protected DataSources initialValue() {
            return DataSources.MASTER;
        }
    };

    /**
     * @return 当前线程所对应的线程局部变量
     */
    public static DataSources get() {
        return dataSourceTypes.get();
    }

    /**
     * 设置当前线程的线程局部变量的值
     *
     * @param dataSourceType 数据源类型
     */
    public static void set(DataSources dataSourceType) {
        dataSourceTypes.set(dataSourceType);
    }

    public static void reset() {
        dataSourceTypes.set(DataSources.MASTER);
    }
}