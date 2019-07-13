package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.service.OmsOrderReturnReasonService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.OmsOrderReturnReason;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 *订单退货管理 controller
 **/
@RestController
@RequestMapping("/orderReturnReason")
@Api(value = "订单退货管理",description = "订单退货管理接口")
public class OmsOrderReturnReasonController {

    @Autowired
    private OmsOrderReturnReasonService orderReturnReasonService;

    @PostMapping("/create")
    @ApiOperation(value = "添加退货原因",notes = "添加退货原因接口")
    public CommonResult create(@RequestBody OmsOrderReturnReason orderReturnReason){
        int count = orderReturnReasonService.create(orderReturnReason);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页获取退货原因",notes = "分页获取退货原因接口")
    public CommonResult<CommonPage<OmsOrderReturnReason>> list(@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                               @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<OmsOrderReturnReason> list = orderReturnReasonService.list(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除退货原因",notes = "根据id删除退货原因接口")
    public CommonResult delete(@PathVariable("id") Long id){
        int count = orderReturnReasonService.delete(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/deleteAll")
    @ApiOperation(value = "批量删除退货原因",notes = "批量删除退货原因接口")
    public CommonResult deleteAll(@RequestParam("ids") List<Long> ids){
        int count = orderReturnReasonService.deleteAll(ids);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "修改退货原因",notes = "修改退货原因接口")
    public CommonResult update(@PathVariable("id") Long id,@RequestBody OmsOrderReturnReason orderReturnReason){
        int count = orderReturnReasonService.update(id, orderReturnReason);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateStatus")
    @ApiOperation(value = "批量修改退货状态",notes = "批量修改退货状态接接口")
    public CommonResult updateStatus(@RequestParam("ids") List<Long> ids, @RequestParam("status") Integer status){
        int count = orderReturnReasonService.updateStatus(ids, status);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateStatusOne/{id}")
    @ApiOperation(value = "根据id去修改退货状态",notes = "根据id去修改退货状态接口")
    public CommonResult updateStatusOne(@PathVariable("id") Long id,@RequestParam("status") Integer status){
        int count = orderReturnReasonService.updateStatusOne(id, status);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/item/{id}")
    @ApiOperation(value = "根据id去获取退货详情",notes = "根据id去获取退货详情接口")
    public CommonResult getItem(@PathVariable("id") Long id){
        OmsOrderReturnReason item = orderReturnReasonService.getItem(id);
        return CommonResult.success(item);
    }
}
