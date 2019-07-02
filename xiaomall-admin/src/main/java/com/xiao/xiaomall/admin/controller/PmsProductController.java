package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.dto.PmsProductParam;
import com.xiao.xiaomall.admin.dto.PmsProductQueryParam;
import com.xiao.xiaomall.admin.dto.PmsProductResult;
import com.xiao.xiaomall.admin.service.PmsProductService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.CmsPrefrenceArea;
import com.xiao.xiaomall.entity.PmsProduct;
import com.xiao.xiaomall.mapper.PmsProductMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiOperation(value = "根据商品的编号去查询信息",notes = "根据商品的编号去查询信息接口")
    public CommonResult<PmsProductResult> getUpdateInfo(@PathVariable Long id){
        PmsProductResult result = productService.getUpdateInfo(id);
        return CommonResult.success(result);
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "修改商品信息",notes = "修改商品信息接口")
    public CommonResult update(@PathVariable Long id,@RequestBody PmsProductParam productParam){
        int count = productService.update(id, productParam);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取商品管理分页",notes = "获取商品管理分页接口")
    public CommonResult<CommonPage<PmsProduct>> list(PmsProductQueryParam productQueryParam,
                                                     @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<PmsProduct> list = productService.list(productQueryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @PostMapping("/update/verifyStatus")
    @ApiOperation(value = "批量修改审核状态",notes = "批量修改审核状态接口")
    public CommonResult updateVerifyStatus(@RequestParam("ids") List<Long> ids,@RequestParam("verifyStatus") Integer verifyStatus, @RequestParam("detail") String detail){
        int count = productService.updateVerifyStatus(ids, verifyStatus, detail);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
}

