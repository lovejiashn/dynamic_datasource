package com.jiashn.dynamic_datasource.dynamic.controller;

import com.jiashn.dynamic_datasource.dynamic.aop.DS;
import com.jiashn.dynamic_datasource.dynamic.domain.TestUser;
import com.jiashn.dynamic_datasource.dynamic.mapper.TestUserMapper;
import com.jiashn.dynamic_datasource.dynamic.util.DataSourceContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: jiangjs
 * @description:
 * @date: 2023/7/27 13:53
 **/
@RestController
@RequestMapping("/test")
public class DynamicDataSourceTest {
    
    @Resource
    private TestUserMapper testUserMapper;

    @GetMapping("/getData.do/{datasourceName}")
    public String getMasterData(@PathVariable("datasourceName") String datasourceName){
        DataSourceContextHolder.setDataSource(datasourceName);
        TestUser testUser = testUserMapper.selectOne(null);
        DataSourceContextHolder.removeDataSource();
        return testUser.getUserName();
    }

    @GetMapping("/getMasterData.do")
    public String getMasterData(){
        TestUser testUser = testUserMapper.selectOne(null);
        return testUser.getUserName();
    }

    @GetMapping("/getSlaveData.do")
    @DS("slave")
    public String getSlaveData(){
        TestUser testUser = testUserMapper.selectOne(null);
        return testUser.getUserName();
    }
}
