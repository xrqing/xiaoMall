package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.service.SmsHomeRecommendProductService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.SmsHomeRecommendProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ConcurrentModificationException;
import java.util.List;

/**
 *首页人气推荐 controller
 **/
@RestController
@RequestMapping("/homeRecommendProduct")
@Api(value = "首页人气推荐",description = "首页人气推荐接口")
public class SmsHomeRecommendProductController {

    @Autowired
    private SmsHomeRecommendProductService homeRecommendProductService;

    @PostMapping("/add")
    @ApiOperation(value = "增加人气推荐",notes = "增加人气推荐接口")
    public CommonResult add(@RequestBody List<SmsHomeRecommendProduct> homeRecommendProductList){
        int count = homeRecommendProductService.add(homeRecommendProductList);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/deleteOne/{id}")
    @ApiOperation(value = "根据id删除人气推荐",notes = "根据id删除人气推荐接口")
    public CommonResult deleteOne(@PathVariable("id") Long id){
        int count = homeRecommendProductService.deleteOne(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }


    @PostMapping("/deleteBatch")
    @ApiOperation(value = "根据id批量删除人气推荐",notes = "根据id批量删除人气推荐接口")
    public CommonResult deleteBatch(@RequestParam List<Long> ids){
        int count = homeRecommendProductService.deleteBatch(ids);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateRecommendStatusOne/{id}")
    @ApiOperation(value = "根据id设置人气推荐",notes = "根据id设置人气推荐状态接口")
    public CommonResult updateRecommendStatusOne(@PathVariable("id") Long id,Integer recommendStatus){
        int count = homeRecommendProductService.updateRecommendStatusOne(id, recommendStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateRecommendStatusBatch")
    @ApiOperation(value = "根据id批量设置人气推荐",notes = "根据id批量设置人气推荐接口")
    public CommonResult updateRecommendStatusBatch(@RequestParam List<Long> ids,Integer recommendStatus){
        int count = homeRecommendProductService.updateRecommendStatusBatch(ids, recommendStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateSort/{id}")
    @ApiOperation(value = "根据id去设置排序",notes = "根据id设置排序接口")
    public CommonResult updateSort(@PathVariable("id") Long id,Integer sort){
        int count = homeRecommendProductService.updateSort(id, sort);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据推荐名称和状态获取分页",notes = "根据推荐名称和状态获取分页接口")
    public CommonResult<CommonPage<SmsHomeRecommendProduct>> list(@RequestParam(value = "productName",required = false) String productName,
                                                                  @RequestParam(value = "recommendStatus",required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<SmsHomeRecommendProduct> list = homeRecommendProductService.list(productName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
