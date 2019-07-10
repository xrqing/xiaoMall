package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.service.OmsOrderSettingService;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.OmsOrderSetting;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *订单设置  controller
 **/
@RestController
@RequestMapping("/orderSetting")
@Api(value = "订单设置",description = "订单设置接口")
public class OmsOrderSettingController {

    @Autowired
    private OmsOrderSettingService orderSettingService;

    @GetMapping("/item/{id}")
    @ApiOperation(value = "根据id去获取指定的订单",notes = "根据id去获取指定的订单设置接口")
    public CommonResult getItem(@PathVariable Long id){
        OmsOrderSetting item = orderSettingService.getItem(id);
        return CommonResult.success(item);
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "根据id去修改指定的订单设置",notes = "根据id去修改指定的订单设置接口")
    public CommonResult update(@PathVariable Long id, @RequestBody OmsOrderSetting orderSetting){
        int count = orderSettingService.update(id, orderSetting);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
}
