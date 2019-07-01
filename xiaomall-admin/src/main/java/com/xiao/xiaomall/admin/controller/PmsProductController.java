package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.dto.PmsProductParam;
import com.xiao.xiaomall.admin.dto.PmsProductResult;
import com.xiao.xiaomall.admin.service.PmsProductService;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.mapper.PmsProductMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *商品管理 controller
 **/
@RestController
@RequestMapping("/product")
@Api(value = "商品管理",description = "商品管理接口")
public class PmsProductController {

    @Autowired
    private PmsProductService productService;

    @PostMapping("/create")
    @ApiOperation(value = "增加商品",notes = "增加商品接口")
    public CommonResult create(@RequestBody PmsProductParam pmsProductParam, BindingResult bindingResult){
        int count = productService.create(pmsProductParam);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/updateInfo/{id}")
    @ApiOperation(value = "根据商品的编号去查询更新的信息",notes = "根据商品的编号去查询更新的信息接口")
    public CommonResult<PmsProductResult> getUpdateInfo(@PathVariable Long id){
        PmsProductResult result = productService.getUpdateInfo(id);
        return CommonResult.success(result);
    }
}

