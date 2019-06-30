package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.service.PmsSkuStockService;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.PmsSkuStock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *商品库存 controller
 **/
@RestController
@Api(value = "商品库存管理",description = "商品库存接口")
@RequestMapping("/skuStock")
public class PmsSkuStockController {

    @Autowired
    private PmsSkuStockService pmsSkuStockService;

    @GetMapping("/list/{productId}")
    @ApiOperation(value = "模糊查询",notes = "模糊查询接口")
    public CommonResult getList(@PathVariable Long productId, @RequestParam(value = "skuStock",required = false ) String skuStock){
        List<PmsSkuStock> list = pmsSkuStockService.getList(productId, skuStock);
        return CommonResult.success(list);
    }

    @PostMapping("/update/{productId}")
    @ApiOperation(value = "批量修改库存信息",notes = "批量修改库存信息接口")
    public CommonResult updateStock(@PathVariable Long productId,@RequestBody List<PmsSkuStock> skuStockList){
        int count  = pmsSkuStockService.updateStock(productId, skuStockList);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
}
