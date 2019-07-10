package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.dto.*;
import com.xiao.xiaomall.admin.service.OmsOrderService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.OmsOrder;
import com.xiao.xiaomall.entity.OmsOrderOperateHistory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.cldr.ar.CalendarData_ar_OM;

import javax.validation.constraints.PastOrPresent;
import java.security.acl.LastOwnerException;
import java.util.List;

/**
 *订单管理  controller
 **/
@RestController
@RequestMapping("/order")
@Api(value = "订单管理",description = "订单管理接口")
public class OmsOrderController {

    @Autowired
    private OmsOrderService orderService;

    @GetMapping("/list")
    @ApiOperation(value = "查询订单",notes = "查询订单接口")
    public CommonResult<CommonPage<OmsOrder>> list(OmsOrderQueryParam queryParam,
                                                   @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<OmsOrder> list = orderService.list(queryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @PostMapping("/delivery")
    @ApiOperation(value = "批量发货",notes = "批量发货接口")
    public CommonResult delivery(@RequestBody List<OmsOrderdeliveryParam> deliveryParamList){
        int count = orderService.delivery(deliveryParamList);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/note")
    @ApiOperation(value = "批量修改",notes = "批量修改订单接口")
    public CommonResult update(@RequestParam("ids") List<Long> ids,String note){
        int count = orderService.update(ids, note);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "批量删除订单",notes = "批量删除订单接口")
    public CommonResult delete(@RequestParam("ids")List<Long> ids){
        int count = orderService.delete(ids);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据id去获取订单详情",notes = "根据id去获取订单详情接口")
    public CommonResult getDetail(@PathVariable Long id){
        OmsOrderDetail detail = orderService.detail(id);
        return CommonResult.success(detail);
    }

    @PostMapping("/update/receiverInfo")
    @ApiOperation(value = "修改收货人信息",notes = "修改收货人信息接口")
    public CommonResult updateReceiverInfo(@RequestBody OmsReceiverInfoParam receiverInfoParam){
        int count = orderService.updateReceiverInfo(receiverInfoParam);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/moneyInfo")
    @ApiOperation(value ="修改订单的费用信息",notes = "修改订单的费用信息接口")
    public CommonResult updateMoneyInfo(@RequestBody OmsMoneyInfoParam moneyInfoParam){
        int count = orderService.updateMoneyInfo(moneyInfoParam);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/noteInfo")
    @ApiOperation(value="修改订单备注信息",notes = "修改订单备注信息接口")
    public CommonResult updateNoteInfo(@RequestParam("id") Long id,@RequestParam("note") String note,@RequestParam("status") Integer status){
        int count = orderService.update(id, note, status);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
}
