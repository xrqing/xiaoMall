package com.xiao.xiaomall.protal.controller;

import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.protal.domain.MemberReadHistory;
import com.xiao.xiaomall.protal.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *会员浏览历史记录  controller
 **/
@RestController
@RequestMapping("/memberReadHistory")
@Api(value ="会员浏览历史记录",description = "会员浏览历史记录接口")
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService readHistoryService;


    @PostMapping("/add")
    @ApiOperation(value = "增加会员历史记录",notes = "增加会员历史记录接口")
    public CommonResult add(@RequestBody MemberReadHistory readHistory){
        int count = readHistoryService.add(readHistory);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/deleteAll")
    @ApiOperation(value = "批量删除会员历史记录",notes = "批量删除会员历史记录接口")
    public CommonResult deleteAll(@RequestParam List<String> ids){
        int count = readHistoryService.delete(ids);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list/{memberId}")
    @ApiOperation(value = "查找会员历史记录",notes ="查询会员历史记录接口")
    public CommonResult<List<MemberReadHistory>> list(@PathVariable("memberId") Long memberId){
        List<MemberReadHistory> list = readHistoryService.list(memberId);
        return CommonResult.success(list);
    }
}
