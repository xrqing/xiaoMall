package com.xiao.xiaomall.protal.controller;

import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.CmsSubject;
import com.xiao.xiaomall.entity.PmsProduct;
import com.xiao.xiaomall.entity.PmsProductCategory;
import com.xiao.xiaomall.protal.domain.HomeContentResult;
import com.xiao.xiaomall.protal.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import java.util.List;

/**
 *首页内容管理  controller
 **/

@RestController
@RequestMapping("/home")
@Api(value = "首页内容管理",description = "首页内容管理接口")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/content")
    @ApiOperation(value = "获取首页内容",notes = "获取首页内容接口")
    public CommonResult<HomeContentResult> content(){
        HomeContentResult content = homeService.content();
        return CommonResult.success(content);
    }

    @GetMapping("/recommendProductList")
    @ApiOperation(value = "首页商品推荐",notes = "首页商品推荐接口")
    public CommonResult<List<PmsProduct>> getRecommendProductList(@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                                        @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<PmsProduct> pmsProducts = homeService.recommendProductList(pageSize, pageNum);
        return CommonResult.success(pmsProducts);
    }

    @GetMapping("/pmsProductCateList/{parentId}")
    @ApiOperation(value = "获取商品分类",notes = "获取商品分类接口")
    public CommonResult<List<PmsProductCategory>> getPmsProductCateList(@PathVariable Long parentId ){
        List<PmsProductCategory> list = homeService.getProductCateList(parentId);
        return CommonResult.success(list);
    }

    @GetMapping("/cmsSubjectList/{categoryId}")
    @ApiOperation(value = "获取专题",notes = "获取专题接口")
    public CommonResult<List<CmsSubject>> getCmsSubjectList(@PathVariable Long categoryId,
                                                            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<CmsSubject> list = homeService.getSubjectList(categoryId, pageSize, pageNum);
        return CommonResult.success(list);
    }
}
