package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.dto.PmsBrandParam;
import com.xiao.xiaomall.admin.service.PmsBrandService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.PmsBrand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PastOrPresent;
import java.awt.*;
import java.util.List;

/**
 * 商品品牌管理 controller
 **/
@RestController
@Api(value = "PmsBrandController",description = "商品品牌管理接口")
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    @GetMapping("/listAll")
    @ApiOperation(value = "查询全部的商品品牌",notes = "查询全部的商品品牌接口")
    public CommonResult<List<PmsBrand>> listAll(){
        List<PmsBrand> brand = brandService.listAllBrand();
        return CommonResult.success(brand);
    }

    @PostMapping("create")
    @ApiOperation(value = "增加商品品牌",notes = "增加商品品牌接口")
    public CommonResult create(@RequestBody PmsBrandParam brandParam) {
        int count = brandService.createBrand(brandParam);
        if (count==1){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "修改商品品牌",notes = "修改商品品牌接口")
    public CommonResult update(@PathVariable Long id,@RequestBody PmsBrandParam pmsBrandParam){
        int count = brandService.updateBrand(id, pmsBrandParam);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除商品品牌",notes = "删除商品品牌接口")
    public CommonResult delete(@PathVariable Long id){
        int count = brandService.delete(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/deleteAll")
    @ApiOperation(value = "批量删除商品品牌",notes = "批量删除商品品牌接口")
    public CommonResult deleteAll(@RequestParam List<Long> ids){
        int count = brandService.deleteAll(ids);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页获取商品品牌",notes = "分页获取商品品牌接口")
    public CommonResult<CommonPage<PmsBrand>> list(@RequestParam(value = "keyword",required = false) String keyword,
                                                   @RequestParam(value = "pageSize",defaultValue = "1") Integer pageSize,
                                                   @RequestParam(value = "pageNum",defaultValue = "5") Integer pageNum){
        List<PmsBrand> list = brandService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/findOne/{id}")
    @ApiOperation(value = "查询单个商品品牌",notes = "查询单个商品品牌接口")
    public CommonResult<PmsBrand> findOne(@PathVariable("id") Long id){
        PmsBrand one = brandService.findOne(id);
        return CommonResult.success(one);
    }

    @PostMapping("/update/showStatus")
    @ApiOperation(value = "批量修改显示状态",notes = "批量修改显示状态接口")
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,@RequestParam("showStatus") Integer showStatus){
        int count = brandService.updateShowStatus(ids, showStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/factoryStatus")
    @ApiOperation(value = "批量修改制造状态",notes = "批量修改制造状态接口")
    public CommonResult updateFactoryStatus(@RequestParam("ids") List<Long> ids, @RequestParam("factoryStatus") Integer factoryStatus){
        int count = brandService.updateFactoryStatus(ids, factoryStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }



}
