package com.jiashn.dynamic_datasource.dynamic.util;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangjs
 * @description: 设置数据源
 * @date: 2023/7/27 11:34
 **/
@Configuration
public class DateSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource createDynamicDataSource(){
        Map<Object,Object> dataSourceMap = new HashMap<>();
        DataSource defaultDataSource = masterDataSource();
        dataSourceMap.put("master",defaultDataSource);
        dataSourceMap.put("slave",slaveDataSource());
        return new DynamicDataSource(defaultDataSource,dataSourceMap);
    }

}
