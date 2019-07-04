package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.dto.PmsProductCategoryParam;
import com.xiao.xiaomall.admin.dto.PmsProductCategoryWithChildrenItem;
import com.xiao.xiaomall.admin.service.PmsProductCategoryService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.PmsProductCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.acl.LastOwnerException;
import java.util.List;

/**
 * 商品分类管理 controller
 **/
@RestController
@Api(value = "商品分类管理",description = "商品分类管理接口")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Autowired
    private PmsProductCategoryService productCategoryService;

    @PostMapping("/add")
    @ApiOperation(value = "增加商品分类",notes = "增加商品分类接口")
    public CommonResult add(@RequestBody PmsProductCategoryParam productCategoryParam){
        int count = productCategoryService.add(productCategoryParam);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "修改商品分类",notes = "修改商品分类接口")
    public CommonResult update(@PathVariable Long id,@RequestBody PmsProductCategoryParam productCategoryParam){
        int count = productCategoryService.update(id, productCategoryParam);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list/{parentId}")
    @ApiOperation(value = "分页获取商品类别",notes = "分页获取商品类别接口")
    public CommonResult<CommonPage<PmsProductCategory>> list(@PathVariable Long parentId,
                                                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                             @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<PmsProductCategory> list = productCategoryService.list(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/item/{id}")
    @ApiOperation(value ="获取商品分类",notes = "获取商品分类接口")
    public CommonResult<PmsProductCategory> getItem(@PathVariable Long id){
        PmsProductCategory item = productCategoryService.getItem(id);
        return CommonResult.success(item);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除商品分类",notes = "删除商品分类接口")
    public CommonResult delete(@PathVariable Long id){
        int count = productCategoryService.delete(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/navStatus")
    @ApiOperation(value = "修改导航栏显示状态",notes = "修改导航栏显示状态接口")
    public CommonResult updateNavStatus(@RequestParam List<Long> ids, Integer navStatus){
        int count = productCategoryService.updateNavStatus(ids, navStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/showStatus")
    @ApiOperation(value = "批量修改显示状态",notes = "批量修改显示状态接口")
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,@RequestParam Integer showStatus){
        int count = productCategoryService.updateShowStatus(ids, showStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list/withChildren")
    @ApiOperation(value = "查询一级及子分类",notes = "查询一级及子分类接口")
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren(){
        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
        return CommonResult.success(list);
    }
}
