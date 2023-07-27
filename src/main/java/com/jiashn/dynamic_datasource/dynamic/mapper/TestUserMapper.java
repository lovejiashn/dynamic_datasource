package com.jiashn.dynamic_datasource.dynamic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiashn.dynamic_datasource.dynamic.domain.TestUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: jiangjs
 * @description:
 * @date: 2023/7/27 14:04
 **/
@Mapper
public interface TestUserMapper extends BaseMapper<TestUser> {
}
