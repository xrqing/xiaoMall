package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.dto.SmsCouponParam;
import com.xiao.xiaomall.admin.service.SmsCouponService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.SmsCoupon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.cldr.ar.CalendarData_ar_OM;

import java.util.ConcurrentModificationException;
import java.util.List;

/**
 *优惠卷管理 controller
 **/
@RestController
@RequestMapping("/coupon")
@Api(value = "优惠卷管理",description = "优惠卷管理接口")
public class SmsCouponController {

    @Autowired
    private SmsCouponService couponService;

    @PostMapping("/add")
    @ApiOperation(value ="增加优惠卷",notes = "增加优惠卷接口")
    public CommonResult add(@RequestBody SmsCouponParam couponParam){
        int count = couponService.add(couponParam);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除优惠卷",notes = "根据id删除优惠卷接口")
    public CommonResult delete(@PathVariable("id") Long id){
        int count = couponService.delete(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/item/{id}")
    @ApiOperation(value = "根据id去获取优惠卷详情",notes = "根据id去获取优惠卷详情接口")
    public CommonResult getItem(@PathVariable("id") Long id){
        SmsCouponParam item = couponService.getItem(id);
        return CommonResult.success(item);
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "根据id修改优惠卷信息",notes = "根据id修改优惠卷信息接口")
    public CommonResult update(@PathVariable("id") Long id,@RequestBody SmsCouponParam couponParam){
        int count = couponService.update(id, couponParam);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据优惠卷名称和类型获取分页",notes = "根据优惠卷名称和类型获取分页接口")
    public CommonResult<CommonPage<SmsCoupon>> list(@RequestParam(value = "name",required = false) String name,
                                                    @RequestParam(value = "type",required = false) Integer type,
                                                    @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        List<SmsCoupon> list = couponService.list(name, type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

}
