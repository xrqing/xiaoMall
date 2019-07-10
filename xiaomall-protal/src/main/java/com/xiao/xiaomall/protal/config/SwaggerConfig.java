package com.xiao.xiaomall.protal.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 *
 * swagger API文档配置
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.xiao.xiaomall.protal.controller"})//扫描controller包
public class SwaggerConfig {

    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("xrq","http://www.xiaoruiqing.cn/","2978183194@qq.com");
        return new ApiInfoBuilder()
                .title("前台管理中心API接口")
                .description("API接口")
                .contact(contact)
                .version("1.1.0")
                .build();
    }
}
