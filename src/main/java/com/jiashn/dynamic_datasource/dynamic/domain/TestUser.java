package com.jiashn.dynamic_datasource.dynamic.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: jiangjs
 * @description:
 * @date: 2023/7/27 14:04
 **/
@Data
@TableName("test_user")
public class TestUser {
    private String userName;
}
