package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.service.SmsHomeNewProductService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.SmsHomeNewProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.PortableInterceptor.HOLDING;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.x509.CertificatePolicyMap;

import java.security.acl.LastOwnerException;
import java.util.List;

/**
 *新品推荐 controller
 **/
@RestController
@RequestMapping("/homeNewProduct")
@Api(value = "新品推荐",description = "新品推荐接口")
public class SmsHomeNewProductController {

    @Autowired
    private SmsHomeNewProductService homeNewProductService;

    @PostMapping("/add")
    @ApiOperation(value = "增加新品推荐",notes = "增加新品推荐接口")
    public CommonResult add(@RequestBody List<SmsHomeNewProduct> homeNewProductList){
        int count = homeNewProductService.add(homeNewProductList);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据id去删除新品推荐",notes = "根据id去删除新品推荐接口")
    public CommonResult delete(@PathVariable("id") Long id){
        int count = homeNewProductService.delete(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/deleteBatch")
    @ApiOperation(value = "根据id去批量删除新品推荐",notes = "根据id去批量删除新品推荐接口")
    public CommonResult deleteBatch(@RequestParam("ids") List<Long> ids){
        int count = homeNewProductService.deleteBatch(ids);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateRecommendStatusOne/{id}")
    @ApiOperation(value = "根据id去设置推荐状态",notes = "根据id去设置推荐状态接口")
    public CommonResult updateRecommendStatusOne(@PathVariable("id") Long id,Integer recommendStatus){
        int count = homeNewProductService.updateRecommendStatusOne(id, recommendStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateRecommendStatus")
    @ApiOperation(value = "根据id批量设置推荐状态",notes = "根据id批量设置推荐状态接口")
    public CommonResult updateRecommendStatus(@RequestParam("ids")List<Long> ids,Integer recommendStatus){
        int count = homeNewProductService.updateRecommendStatus(ids, recommendStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateSortOne/{id}")
    @ApiOperation(value = "根据id设置排序",notes = "根据id设置排序接口")
    public CommonResult updateSortOne(@PathVariable("id") Long id,Integer sort){
        int count = homeNewProductService.updateSortOne(id, sort);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateSort")
    @ApiOperation(value = "根据id批量设置排序",notes = "根据id批量设置排序接口")
    public CommonResult updateSort(@RequestParam("ids") List<Long> ids,Integer sort){
        int count = homeNewProductService.updateSort(ids, sort);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询（根据新品名称和推荐状态）",notes = "分页查询接口（根据新品名称和推荐状态）")
    public CommonResult<CommonPage<SmsHomeNewProduct>> list(@RequestParam(value = "productName",required = false) String productName,
                                                            @RequestParam(value = "recommendStatus",required = false) Integer recommendStatus,
                                                            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<SmsHomeNewProduct> list = homeNewProductService.list(productName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
