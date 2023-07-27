package com.jiashn.dynamic_datasource.dynamic.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: jiangjs
 * @description: 数据源实体
 * @date: 2023/7/27 15:55
 **/
@Data
@Accessors(chain = true)
public class DataSourceEntity {

    /**
     * 数据库地址
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 数据库驱动
     */
    private String driverClassName;
    /**
     * 数据库key，即保存Map中的key
     */
    private String key;
}
