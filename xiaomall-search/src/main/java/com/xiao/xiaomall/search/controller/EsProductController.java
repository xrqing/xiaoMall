package com.xiao.xiaomall.search.controller;

import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.search.domain.EsProduct;
import com.xiao.xiaomall.search.domain.EsProductRelatedInfo;
import com.xiao.xiaomall.search.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索商品管理Controller
 **/
@RestController
@RequestMapping("/esProduct")
@Api(value = "搜索商品管理",description = "搜索商品管理的接口")
public class EsProductController {

    @Autowired
    private EsProductService productService;

    @PostMapping("/importAll")
    @ApiOperation(value = "导入所有数据库的商品到es",notes = "导入所有数据库中的商品到es接口")
    public CommonResult<Integer> importAllList(){
        int count = productService.importAll();
        return CommonResult.success(count);
    }

    @GetMapping("/search/simple")
    @ApiOperation(value = "简单搜索",notes = "简单搜索接口")
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false,defaultValue = "5") Integer pageSize,
                                                      @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<EsProduct> search = productService.search(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(search));
    }

    @PostMapping("/create/{id}")
    @ApiOperation(value = "根据id去创建商品",notes = "根据id去创建商品接口")
    public CommonResult<EsProduct> create(@PathVariable Long id){
        EsProduct product = productService.create(id);
        if (product!=null){
            return CommonResult.success(product);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据id去删除商品",notes = "根据id去删除商品接口")
    public CommonResult<Object> delete(@PathVariable Long id){
        productService.delete(id);
        return CommonResult.success(null);
    }

    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除",notes = "批量删除接口")
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids){
        productService.delete(ids);
        return CommonResult.success(null);
    }

    @GetMapping("/search")
    @ApiOperation(value = "综合搜索、筛选、排序",notes = "综合搜索、筛选、排序接口")
    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低",
            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false)String keyword,
                                                      @RequestParam(required = false) Long brandId,
                                                      @RequestParam(required = false) Long productCategoryId,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                      @RequestParam(required = false, defaultValue = "0") Integer sort){
        Page<EsProduct> search = productService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return CommonResult.success(CommonPage.restPage(search));
    }

    @PostMapping("/recommend/{id}")
    @ApiOperation(value = "根据id去推荐商品",notes = "根据id去推荐商品接口")
    public CommonResult<CommonPage<EsProduct>> recommend(@PathVariable Long id,
                                                         @RequestParam(value = "pageSize",defaultValue = "0",required = false) Integer pageSize,
                                                         @RequestParam(value = "pageNum",defaultValue = "5",required = false) Integer pageNum){
        Page<EsProduct> recommend = productService.recommend(id, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(recommend));
    }

    @GetMapping("/searchRelated/info")
    @ApiOperation(value = "获取搜索的相关品牌、分类及筛选属性",notes = "获取搜索的相关品牌、分类及筛选属性接口")
    public CommonResult<EsProductRelatedInfo> searchRelatedInfo(@RequestParam(required = false) String keyword) {
        EsProductRelatedInfo productRelatedInfo = productService.searchRelatedInfo(keyword);
        return CommonResult.success(productRelatedInfo);
    }
}
