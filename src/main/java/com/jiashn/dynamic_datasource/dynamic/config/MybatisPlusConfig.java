package com.jiashn.dynamic_datasource.dynamic.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: jiangjs
 * @description: mybatis-plus配置
 * @date: 2023/7/27 11:11
 **/
@MapperScan("com.jiashn.**.mapper.**")
@Configuration
public class MybatisPlusConfig {

    /**
     * 配置3.2.0分页
     * @return 返回分页拦截器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
