package com.xiao.xiaomall.protal.controller;

import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.UmsMember;
import com.xiao.xiaomall.protal.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *会员管理controller
 **/
@RestController
@RequestMapping("/umsMember")
@Api(value = "会员管理",description = "会员管理接口")
public class UmsMemberController {

    @Autowired
    private UmsMemberService memberService;

    @GetMapping("/username")
    @ApiOperation(value = "根据用户名获取会员",notes = "根据用户名获取会员接口")
    public CommonResult getByUsername(@RequestParam String username){
        UmsMember umsMember = memberService.getByUsername(username);
        return CommonResult.success(umsMember);
    }
}
