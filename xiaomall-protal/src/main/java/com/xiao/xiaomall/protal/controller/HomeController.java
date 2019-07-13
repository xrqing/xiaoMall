package com.xiao.xiaomall.protal.controller;

import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.protal.domain.HomeContentResult;
import com.xiao.xiaomall.protal.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *首页内容管理  controller
 **/

@RestController
@RequestMapping("/home")
@Api(value = "首页内容管理",description = "首页内容管理接口")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/content")
    @ApiOperation(value = "获取首页内容",notes = "获取首页内容接口")
    public CommonResult<HomeContentResult> content(){
        HomeContentResult content = homeService.content();
        return CommonResult.success(content);
    }
}
