package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.dto.PmsProductAttributeCategoryItem;
import com.xiao.xiaomall.admin.service.PmsProductAttributeCategoryService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.PmsProductAttributeCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *商品属性分类管理 controller
 **/
@RestController
@RequestMapping("/productAttributeCategory")
@Api(value = "商品属性分类管理",description = "商品属性分类管理接口")
public class PmsProductAttributeCategoryController {

    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @PostMapping("/add")
    @ApiOperation(value = "增加商品属性分类",notes = "增加商品属性分类接口")
    public CommonResult add(@RequestParam String name){
        int count = productAttributeCategoryService.add(name);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "修改商品分类属性",notes = "修改商品分类属性接口")
    public CommonResult update(@PathVariable Long id,@RequestParam String name){
        int count = productAttributeCategoryService.update(id,name);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除商品属性分类",notes = "根据id删除商品属性分类接口")
    public CommonResult delete(@PathVariable Long id){
        int count = productAttributeCategoryService.delete(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/item/{id}")
    @ApiOperation(value = "根据id查询商品分类属性",notes = "根据id查询商品分类属性接口")
    public CommonResult getItem(@PathVariable Long id){
        PmsProductAttributeCategory item = productAttributeCategoryService.getItem(id);
        return CommonResult.success(item);
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询商品分类属性",notes = "分页查询商品分类属性接口")
    public CommonResult<CommonPage<PmsProductAttributeCategory>> list(@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                                      @RequestParam(value = "pageNum",defaultValue = " 1") Integer pageNum){
        List<PmsProductAttributeCategory> list = productAttributeCategoryService.list(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/list/withAttr")
    @ApiOperation(value = "获取所有商品属性分类及其下属性",notes = "获取所有商品属性分类及其下属性接口")
    public CommonResult<List<PmsProductAttributeCategoryItem>> getListWithAttr(){
        List<PmsProductAttributeCategoryItem> list = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(list);
    }
}
