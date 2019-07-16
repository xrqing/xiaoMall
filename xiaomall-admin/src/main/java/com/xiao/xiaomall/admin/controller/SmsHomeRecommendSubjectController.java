package com.xiao.xiaomall.admin.controller;


import com.xiao.xiaomall.admin.service.SmsHomeRecommendSubjectService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.SmsHomeRecommendSubject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 *专题推荐   controller
 **/
@RestController
@RequestMapping("/homeRecommendSubject")
@Api(value = "专题推荐",description = "专题推荐接口")
public class SmsHomeRecommendSubjectController {

    @Autowired
    private SmsHomeRecommendSubjectService homeRecommendSubjectService;

    @PostMapping("/add")
    @ApiOperation(value = "增加专题推荐",notes = "增加专题推荐接口")
    public CommonResult add(@RequestBody List<SmsHomeRecommendSubject> homeRecommendSubjectList){
        int count = homeRecommendSubjectService.add(homeRecommendSubjectList);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/deleteOne/{id}")
    @ApiOperation(value = "根据id删除专题推荐",notes = "根据id删除专题推荐接口")
    public CommonResult deleteOne(@PathVariable("id") Long id){
        int count = homeRecommendSubjectService.deleteOne(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/deleteBatch")
    @ApiOperation(value = "根据id批量删除专题推荐",notes = "根据id批量删除专题推荐接口")
    public CommonResult deleteBatch(@RequestParam("ids")List<Long> ids){
        int count = homeRecommendSubjectService.deleteBatch(ids);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateRecommendStatusOne/{id}")
    @ApiOperation(value = "根据id设置推荐状态",notes = "根据id设置推荐状态接口")
    public CommonResult updateRecommendStatusOne(@PathVariable("id")Long id,Integer recommendStatus){
        int count = homeRecommendSubjectService.updateRecommendStatusOne(id, recommendStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateRecommendStatus")
    @ApiOperation(value = "根据id批量设置推荐状态",notes = "根据id批量设置推荐状态接口")
    public CommonResult updateRecommendStatusBatch(@RequestParam("ids") List<Long> ids,Integer recommendStatus){
        int count = homeRecommendSubjectService.updateRecommendStatusBatch(ids, recommendStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateSort/{id}")
    @ApiOperation(value = "根据id设置排序",notes = "根据id设置排序接口")
    public CommonResult updateSort(@PathVariable("id") Long id,Integer sort){
        int count = homeRecommendSubjectService.updateSort(id, sort);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据推荐专题和名称获取分页",notes = "根据专题名称和状态获取分页接口")
    public CommonResult<CommonPage<SmsHomeRecommendSubject>> list(@RequestParam(value = "subjectName",required = false) String subjectName,
                                                                  @RequestParam(value = "recommendStatus",required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<SmsHomeRecommendSubject> list = homeRecommendSubjectService.list(subjectName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
