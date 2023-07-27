package com.jiashn.dynamic_datasource.dynamic.util;

/**
 * @author: jiangjs
 * @description:
 * @date: 2023/7/27 11:21
 **/
public class DataSourceContextHolder {
    //此类提供线程局部变量。这些变量不同于它们的正常对应关系是每个线程访问一个线程(通过get、set方法),有自己的独立初始化变量的副本。
    private static final ThreadLocal<String> DATASOURCE_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dataSourceName 数据源名称
     */
    public static void setDataSource(String dataSourceName){
        DATASOURCE_HOLDER.set(dataSourceName);
    }

    /**
     * 获取当前线程的数据源
     * @return 数据源名称
     */
    public static String getDataSource(){
        return DATASOURCE_HOLDER.get();
    }

    /**
     * 删除当前数据源
     */
    public static void removeDataSource(){
        DATASOURCE_HOLDER.remove();
    }

}
