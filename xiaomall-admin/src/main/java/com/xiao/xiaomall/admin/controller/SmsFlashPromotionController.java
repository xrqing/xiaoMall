package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.service.SmsFlashPromotionService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.SmsFlashPromotion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 *限时购物管理 controller
 **/
@RestController
@RequestMapping("/flashPromotion")
@Api(value = "限时购物管理",description = "限时购物管理接口")
public class SmsFlashPromotionController {

    @Autowired
    private SmsFlashPromotionService flashPromotionService;

    @PostMapping("/add")
    @ApiOperation(value = "增加限时购物",notes = "增加限时购物接口")
    public CommonResult add(@RequestBody SmsFlashPromotion flashPromotion){
        int count = flashPromotionService.add(flashPromotion);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除限时购物",notes = "根据id删除限时购物接口")
    public CommonResult delete(@PathVariable("id") Long id){
        int count = flashPromotionService.delete(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateStatus/{id}")
    @ApiOperation(value = "根据id修改上下线状态",notes = "根据id修改上下线状态接口")
    public CommonResult updateStatus(@PathVariable("id") Long id,Integer status){
        int count = flashPromotionService.updateStatus(id, status);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "根据id修改限时购物",notes = "根据id修改限时购物接口")
    public CommonResult update(@PathVariable("id") Long id,@RequestBody SmsFlashPromotion flashPromotion){
        int count = flashPromotionService.update(id, flashPromotion);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/item/{id}")
    @ApiOperation(value = "=根据id获取详情",notes ="根据id获取详请接口")
    public CommonResult getItem(@PathVariable("id") Long id){
        SmsFlashPromotion item = flashPromotionService.getItem(id);
        return CommonResult.success(item);
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据关键字分页获取限时购物",notes = "根据关键字分页获取限时购物皆接口")
    public CommonResult<CommonPage<SmsFlashPromotion>> list(@RequestParam(value = "keyword",required = false) String keyword,
                                                            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<SmsFlashPromotion> list = flashPromotionService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
