package com.jiashn.dynamic_datasource.dynamic.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: jiangjs
 * @description:
 * @date: 2023/7/27 16:58
 **/
@Data
@TableName("test_db_info")
public class TestDbInfo {

    @TableId(type = IdType.AUTO)
    private Integer id;

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
     * 数据库name，即保存Map中的key
     */
    private String name;
}
