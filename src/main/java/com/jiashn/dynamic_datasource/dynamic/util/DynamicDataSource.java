package com.jiashn.dynamic_datasource.dynamic.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.jiashn.dynamic_datasource.dynamic.domain.DataSourceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: jiangjs
 * @description: 实现动态数据源，根据AbstractRoutingDataSource路由到不同数据源中
 * @date: 2023/7/27 11:18
 **/
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    private final Map<Object,Object> targetDataSourceMap;

    public DynamicDataSource(DataSource defaultDataSource,Map<Object, Object> targetDataSources){
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(targetDataSources);
        this.targetDataSourceMap = targetDataSources;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }

    /**
     * 添加数据源信息
     * @param dataSources 数据源实体集合
     */
    public void createDataSource(List<DataSourceEntity> dataSources){
        try {
            if (CollectionUtils.isNotEmpty(dataSources)){
                for (DataSourceEntity ds : dataSources) {
                    //校验数据库是否可以连接
                    Class.forName(ds.getDriverClassName());
                    DriverManager.getConnection(ds.getUrl(),ds.getUsername(),ds.getPassword());
                    //定义数据库
                    DruidDataSource dataSource = new DruidDataSource();
                    BeanUtils.copyProperties(ds,dataSource);
                    //申请连接时执行validationQuery检测连接是否有效，这里建议配置为TRUE，防止取到的连接不可用
                    dataSource.setTestOnBorrow(true);
                    //建议配置为true，不影响性能，并且保证安全性。
                    //申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
                    dataSource.setTestWhileIdle(true);
                    //用来检测连接是否有效的sql，要求是一个查询语句。
                    dataSource.setValidationQuery("select 1 ");
                    dataSource.init();
                    this.targetDataSourceMap.put(ds.getKey(),dataSource);
                    log.info("------添加数据源-----：" + ds.getKey() + ":成功");
                }
                super.setTargetDataSources(this.targetDataSourceMap);
                // 将TargetDataSources中的连接信息放入resolvedDataSources管理
                super.afterPropertiesSet();
            }
        }catch (ClassNotFoundException | SQLException e) {
            log.error("---程序报错---:{}", e.getMessage());
        }
    }

    /**
     * 校验数据源是否存在
     * @param key 数据源保存的key
     * @return 返回结果，true：存在，false：不存在
     */
    public boolean existsDataSource(String key){
        return Objects.nonNull(this.targetDataSourceMap.get(key));
    }
}
