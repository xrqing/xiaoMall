package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.dto.OmsReturnApplyQueryParam;
import com.xiao.xiaomall.admin.dto.OmsUpdateStatusParam;
import com.xiao.xiaomall.admin.service.OmsOrderReturnApplyService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.OmsOrderReturnApply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *订单退货申请管理  controller
 **/
@RestController
@RequestMapping("/orderReturnApply")
@Api(value = "订单退货处理",description = "订单退货处理接口")
public class OmsOrderReturnApplyController {

    @Autowired
    private OmsOrderReturnApplyService orderReturnApplyService;

    @GetMapping("/list")
    @ApiOperation(value = "分页获取订单退货",notes = "分页获取订单退货接口")
    public CommonResult<CommonPage<OmsOrderReturnApply>> list(OmsReturnApplyQueryParam queryParam,
                                                              @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<OmsOrderReturnApply> list = orderReturnApplyService.list(queryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "批量删除",notes = "批量删除接口")
    public CommonResult delete(@RequestParam("ids") List<Long> ids){
        int count = orderReturnApplyService.delete(ids);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/returnApplyDetail/{id}")
    @ApiOperation(value = "根据id去查询退货订单",notes = "根据id去查询退货订单接口")
    public CommonResult getItem(@PathVariable Long id){
        OmsOrderReturnApply item = orderReturnApplyService.getItem(id);
        return CommonResult.success(item);
    }

    @PostMapping("/updateStatus/{id}")
    @ApiOperation(value = "根据id去修改退货状态(确认和拒绝退货)",notes = "根据id去修改退货状态(确认和拒绝退货)接口")
    public CommonResult updateStatus(@PathVariable Long id, @RequestBody OmsUpdateStatusParam param){
        int count = orderReturnApplyService.updateStatus(id, param);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
}
