package com.xiao.xiaomall.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *MybatisConfig配置类
 **/
@Configuration
@EnableTransactionManagement
@MapperScan({"com.xiao.xiaomall.mapper","com.xiao.xiaomall.admin.dao"})
public class MybatisConfig {

}
