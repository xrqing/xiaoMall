package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.dto.PmsProductAttributeParam;
import com.xiao.xiaomall.admin.dto.ProductAttrInfo;
import com.xiao.xiaomall.admin.service.PmsProductAttributeService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.PmsProductAttribute;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *商品属性管理 controller
 **/
@RestController
@RequestMapping("/productAttribute")
@Api(value ="商品属性管理",description = "商品属性管理接口")
public class PmsProductAttributeController {

    @Autowired
    private PmsProductAttributeService productAttributeService;

    @GetMapping("/list/{productAttributeCategoryId}")
    @ApiOperation(value = "分页获取商品属性",notes = "分页获取商品属性接口")
    public CommonResult<CommonPage<PmsProductAttribute>> list(@PathVariable Long productAttributeCategoryId,
                                                              @RequestParam("type") Integer type,
                                                              @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<PmsProductAttribute> list = productAttributeService.list(productAttributeCategoryId, type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @PostMapping("/add")
    @ApiOperation(value = "增加商品属性",notes = "增加商品属性接口")
    public CommonResult create(@RequestBody PmsProductAttributeParam productAttributeParam, BindingResult bindingResult) {
        int count = productAttributeService.create(productAttributeParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "修改商品属性",notes = "修改商品属性接口")
    public CommonResult update(@PathVariable Long id,@RequestBody PmsProductAttributeParam productAttributeParam,BindingResult bindingResult){
        int count = productAttributeService.update(id, productAttributeParam);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/item/{id}")
    @ApiOperation(value = "根据id去查询商品属性",notes = "根据id去查询商品属性接口")
    public CommonResult<PmsProductAttribute> getItem(@PathVariable Long id){
        PmsProductAttribute item = productAttributeService.getItem(id);
        return CommonResult.success(item);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "批量删除",notes = "批量删除接口")
    public CommonResult delete(@RequestParam("ids") List<Long> ids){
        int count = productAttributeService.delete(ids);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
    @GetMapping("/attrInfo/{productCategoryId}")
    @ApiOperation(value = "根据商品分类的id获取商品属性及属性分类",notes = "根据商品分类的id获取商品属性及属性分类接口")
    public CommonResult<List<ProductAttrInfo>> getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttrInfo> productAttrInfoList = productAttributeService.getProductAttrInfo(productCategoryId);
        return CommonResult.success(productAttrInfoList);
    }
}
