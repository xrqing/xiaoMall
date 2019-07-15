package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.service.SmsHomeBrandService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.SmsHomeBrand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 *品牌推荐 controller
 **/
@RestController
@RequestMapping("/homeBrand")
@Api(value = "品牌推荐",description = "品牌推荐接口")
public class SmsHomeBrandController {

    @Autowired
    private SmsHomeBrandService homeBrandService;

    @PostMapping("/add")
    @ApiOperation(value = "添加品牌",notes = "添加品牌接口")
    public CommonResult add(@RequestBody List<SmsHomeBrand> homeBrandList){
        int count = homeBrandService.add(homeBrandList);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据id去删除品牌推荐",notes = "根据id去删除品牌推荐接口")
    public CommonResult delete(@PathVariable("id") Long id){
        int count = homeBrandService.delete(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/deleteAll")
    @ApiOperation(value = "批量删除品牌推荐",notes = "批量删除品牌推荐接口")
    public CommonResult deleteAll(@RequestParam("ids")List<Long> ids){
        int count = homeBrandService.deleteAll(ids);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateRecommendStatus/{id}")
    @ApiOperation(value = "根据id设置品牌推荐 0->不推荐，1->推荐",notes = "根据id设置品牌推荐接口 0->不推荐，1->推荐")
    public CommonResult updateRecommendStatusOne(@PathVariable("id")Long id, Integer recommendStatus){
        int count = homeBrandService.updateRecommendStatus(id, recommendStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/recommendStatus")
    @ApiOperation(value = "批量修改推荐状态(0->不推荐，1->推荐)",notes = "批量修改推荐状态接口(0->不推荐，1->推荐)")
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,Integer recommendStatus){
        int count = homeBrandService.recommendStatus(ids, recommendStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateSort/{id}")
    @ApiOperation(value = "根据id去修改排序",notes = "根据id去修改排序接口")
    public CommonResult updateSort(@PathVariable("id") Long id,Integer sort){
        int count = homeBrandService.updateSort(id, sort);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list")
    @ApiOperation(value ="分页查询",notes = "分页查询接口")
    public CommonResult<CommonPage<SmsHomeBrand>> list(@RequestParam(value = "brandName",required = false) String brandName,
                                                       @RequestParam(value = "recommendStatus",required = false) Integer recommendStatus,
                                                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<SmsHomeBrand> list = homeBrandService.list(brandName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
