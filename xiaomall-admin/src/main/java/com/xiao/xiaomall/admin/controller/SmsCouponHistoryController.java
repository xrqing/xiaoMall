package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.service.SmsCouponHistoryService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.SmsCouponHistory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *优惠卷管理  controller
 **/
@RestController
@RequestMapping("/couponHistory")
@Api(value = "优惠卷领取历史记入",description = "优惠卷领取历史记入管理接口")
public class SmsCouponHistoryController {

    @Autowired
    private SmsCouponHistoryService couponHistoryService;

    @GetMapping("/list")
    @ApiOperation(value = "分页获取优惠卷领取历史记入管理",notes = "分页获取优惠卷领取历史记入管理接口")
    public CommonResult<CommonPage<SmsCouponHistory>> list(@RequestParam(value = "couponId",required = false) Long couponId,
                                                           @RequestParam(value = "useStatus",required = false)Integer useStatus,
                                                           @RequestParam(value = "orderSn",required = false) String orderSn,
                                                           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<SmsCouponHistory> list = couponHistoryService.list(couponId, useStatus, orderSn, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
