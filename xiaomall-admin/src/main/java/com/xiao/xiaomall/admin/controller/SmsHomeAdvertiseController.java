package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.service.SmsHomeAdvertiseService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.SmsHomeAdvertise;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.monitor.CounterMonitor;
import java.security.acl.LastOwnerException;
import java.util.List;

/**
 *广告推荐  controller
 **/
@RestController
@RequestMapping("/homeAdvertise")
@Api(value = "广告推荐",description = "广告推荐接口")
public class SmsHomeAdvertiseController {

    @Autowired
    private SmsHomeAdvertiseService homeAdvertiseService;

    @PostMapping("/add")
    @ApiOperation(value = "增加广告推荐",notes = "增加广告推荐接口")
    public CommonResult add(@RequestBody SmsHomeAdvertise homeAdvertise){
        int count = homeAdvertiseService.add(homeAdvertise);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除广告推荐",notes = "根据id删除广告推荐接口")
    public CommonResult deleteOne(@PathVariable("id") Long id){
        int count = homeAdvertiseService.deleteOne(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/deleteBatch")
    @ApiOperation(value = "根据id批量删除广告推荐",notes = "根据id批量删除广告推荐接口")
    public CommonResult deleteBatch(@RequestParam("ids")List<Long> ids){
        int count = homeAdvertiseService.deleteBatch(ids);
        if (count>0){
            return CommonResult.success(count);
        }else {
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateStatus/{id}")
    @ApiOperation(value = "根据id去设置广告推荐",notes ="根据id去设置广告推荐状态接口")
    public CommonResult updateStatus(@PathVariable("id")Long id,Integer status){
        int count = homeAdvertiseService.updateStatus(id, status);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "根据id去修改广告推荐",notes = "根据id去修改广告推荐状态接口")
    public CommonResult update(@PathVariable("id") Long id,SmsHomeAdvertise homeAdvertise){
        int count = homeAdvertiseService.update(id, homeAdvertise);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据名称，位置，结束时间获取分页",notes = "根据名称，位置，结束时间获取分页接口")
    public CommonResult<CommonPage<SmsHomeAdvertise>> list(@RequestParam(value = "name",required = false) String name,
                                                           @RequestParam(value = "type",required = false) Integer type,
                                                           @RequestParam(value = "endTime", required = false) String endTime,
                                                           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<SmsHomeAdvertise> list = homeAdvertiseService.list(name, type, endTime, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
