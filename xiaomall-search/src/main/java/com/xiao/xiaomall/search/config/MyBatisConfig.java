package com.xiao.xiaomall.search.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by macro on 2019/4/8.
 */
@Configuration
@MapperScan({"com.xiao.xiaomall.mapper","com.xiao.xiaomall.search.dao"})
public class MyBatisConfig {
}
