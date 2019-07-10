package com.xiao.xiaomall.protal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *MybatisConfig配置类
 **/
@Configuration
@EnableTransactionManagement
@MapperScan({"com.xiao.xiaomall.mapper","com.xiao.xiaomall.protal.dao"})
public class MybatisConfig {

}
