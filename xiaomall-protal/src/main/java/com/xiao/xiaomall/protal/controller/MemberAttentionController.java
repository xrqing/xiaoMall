package com.xiao.xiaomall.protal.controller;

import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.protal.domain.MemberBrandAttention;
import com.xiao.xiaomall.protal.service.MemberAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *会员关注管理  controller
 **/
@RestController
@RequestMapping("/memberAttention")
@Api(value = "会员关注",description = "会员关注接口")
public class MemberAttentionController {

    @Autowired
    private MemberAttentionService memberAttentionService;

    @PostMapping("/add")
    @ApiOperation(value = "添加会员",notes = "添加会员接口")
    public CommonResult add(@RequestBody MemberBrandAttention memberBrandAttention){
        int count = memberAttentionService.add(memberBrandAttention);
        if (count>0){
            return CommonResult.success(count);
        }else {
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "取消会员",notes = "取消会员接口")
    public CommonResult delete(Long memberId,Long brandId){
        int count = memberAttentionService.delete(memberId, brandId);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list/{memberId}")
    @ApiOperation(value = "获取全部会员",notes = "获取全部会员接口")
    public CommonResult<List<MemberBrandAttention>> list(@PathVariable("memberId") Long memberId){
        List<MemberBrandAttention> list = memberAttentionService.list(memberId);
        return CommonResult.success(list);
    }
}

