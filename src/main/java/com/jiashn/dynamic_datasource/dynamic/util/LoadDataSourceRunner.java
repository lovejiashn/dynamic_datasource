package com.jiashn.dynamic_datasource.dynamic.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.jiashn.dynamic_datasource.dynamic.domain.DataSourceEntity;
import com.jiashn.dynamic_datasource.dynamic.domain.TestDbInfo;
import com.jiashn.dynamic_datasource.dynamic.mapper.TestDbInfoMapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: jiangjs
 * @description:
 * @date: 2023/7/27 16:56
 **/
@Component
public class LoadDataSourceRunner implements CommandLineRunner {
    @Resource
    private DynamicDataSource dynamicDataSource;
    @Resource
    private TestDbInfoMapper testDbInfoMapper;
    @Override
    public void run(String... args) throws Exception {
        List<TestDbInfo> testDbInfos = testDbInfoMapper.selectList(null);
        if (CollectionUtils.isNotEmpty(testDbInfos)) {
            List<DataSourceEntity> ds = new ArrayList<>();
            for (TestDbInfo testDbInfo : testDbInfos) {
                DataSourceEntity sourceEntity = new DataSourceEntity();
                BeanUtils.copyProperties(testDbInfo,sourceEntity);
                sourceEntity.setKey(testDbInfo.getName());
                ds.add(sourceEntity);
            }
            dynamicDataSource.createDataSource(ds);
        }
    }
}
