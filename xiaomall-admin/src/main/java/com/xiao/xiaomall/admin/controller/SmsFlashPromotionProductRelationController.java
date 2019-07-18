package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.dto.SmsFlashPromotionProduct;
import com.xiao.xiaomall.admin.service.SmsFlashPromotionProductRelationService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.SmsFlashPromotionProductRelation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 *限时购和商品关系管理  controller
 **/
@RestController
@RequestMapping("/flashPromotionProductRelation")
@Api(value = "限时购和商品关系管理",description = "限时购和商品关系管理接口")
public class SmsFlashPromotionProductRelationController {

    @Autowired
    private SmsFlashPromotionProductRelationService relationService;

    @PostMapping("/add")
    @ApiOperation(value = "增加关联",notes = "增加关联接口")
    public CommonResult add(@RequestBody List<SmsFlashPromotionProductRelation> flashPromotionProductRelationList){
        int count = relationService.add(flashPromotionProductRelationList);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value ="根据id去删除",notes = "根据id删除关联接口")
    public CommonResult delete(@PathVariable("id") Long id){
        int count = relationService.delete(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "根据id去修改关联",notes = "根据id修改关联接口")
    public CommonResult update(@PathVariable("id") Long id,@RequestBody SmsFlashPromotionProductRelation relation){
        int count = relationService.update(id, relation);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询不同场次关联及商品信息接口",notes = "分页查询不同场次关联及商品信息接口")
    public CommonResult<CommonPage<SmsFlashPromotionProduct>> list(@RequestParam(value = "flashPromotionId") Long flashPromotionId,
                                                                   @RequestParam(value = "flashPromotionSessionId") Long flashPromotionSessionId,
                                                                   @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                                   @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<SmsFlashPromotionProduct> list = relationService.list(flashPromotionId, flashPromotionSessionId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
