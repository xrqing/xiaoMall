package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.dto.SmsFlashPromotionSessionDetail;
import com.xiao.xiaomall.admin.service.SmsFlashPromotionService;
import com.xiao.xiaomall.admin.service.SmsFlashPromotionSessionService;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.SmsFlashPromotion;
import com.xiao.xiaomall.entity.SmsFlashPromotionSession;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Getter;
import net.bytebuddy.matcher.CollectionOneToOneMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.x509.CertificatePolicyMap;

import java.util.List;

/**
 *限时购场次管理 controller
 **/
@RestController
@RequestMapping("/flashPromotionSession")
@Api(value = "限时购场次管理",description = "限时购场次管理接口")
public class SmsFlashPromotionSessionController {

    @Autowired
    private SmsFlashPromotionSessionService flashPromotionSessionService;

    @PostMapping("/add")
    @ApiOperation(value = "增加限时购物",notes = "增加限时购物接口")
    public CommonResult add(@RequestBody SmsFlashPromotionSession flashPromotionSession){
        int count = flashPromotionSessionService.add(flashPromotionSession);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value ="根据id删除限时购" ,notes = "根据id删除限时购接口")
    public CommonResult delete(@PathVariable("id") Long id){
        int count = flashPromotionSessionService.delete(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "根据id修改限时购",notes = "根据id修改限时购接口")
    public CommonResult update(@PathVariable("id") Long id,@RequestBody SmsFlashPromotionSession flashPromotionSession){
        int count = flashPromotionSessionService.update(id, flashPromotionSession);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/item/{id}")
    @ApiOperation(value = "根据id获取具体的限时购",notes = "根据id获取具体的限时购接口")
    public CommonResult getItem(@PathVariable("id") Long id){
        SmsFlashPromotionSession item = flashPromotionSessionService.getItem(id);
        return CommonResult.success(item);
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取全部的限时购",notes = "获取全部的限时购接口")
    public CommonResult list(){
        List<SmsFlashPromotionSession> list = flashPromotionSessionService.list();
        return CommonResult.success(list);
    }

    @PostMapping("/updateStatus/{id}")
    @ApiOperation(value = "根据id修改限时购状态",notes = "根据id修改限时购状态接口")
    public CommonResult updateStatus(@PathVariable("id") Long id,Integer status){
        int count = flashPromotionSessionService.updateStatus(id, status);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/selectList")
    @ApiOperation(value = "获取全部可选场次及其数量",notes = "获取全部可选场次及其数量接口")
    public CommonResult<List<SmsFlashPromotionSessionDetail>> selectList(Long flashPromotionId){
        List<SmsFlashPromotionSessionDetail> smsFlashPromotionSessionDetails = flashPromotionSessionService.selectList(flashPromotionId);
        return CommonResult.success(smsFlashPromotionSessionDetails);
    }
}
