package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.service.PmsCommentService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.PmsComment;
import com.xiao.xiaomall.mapper.PmsCommentMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *评价管理 serviceImpl
 **/
@RestController
@RequestMapping("/comment")
@Api(value = "评价管理",description = "评价管理接口")
public class PmsCommentController{

    @Autowired
    private PmsCommentService commentService;

    @PostMapping("/add")
    @ApiOperation(value = "增加评价管理",notes = "增加评价管理接口")
    public CommonResult add(@RequestBody PmsComment comment){
        int count = commentService.add(comment);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/deleteOne/{id}")
    @ApiOperation(value = "根据id删除评价",notes = "根据id删除评价接口")
    public CommonResult deleteOne(@PathVariable("id") Long id){
        int count = commentService.deleteOne(id);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/deleteBatch")
    @ApiOperation(value = "根据id批量删除评价",notes = "根据id批量删除评价接口")
    public CommonResult deleteBatch(@RequestParam("ids")List<Long> ids){
        int count = commentService.deleteBatch(ids);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateShowStatusOne/{id}")
    @ApiOperation(value = "根据id修改显示状态",notes = "根据id修改显示状态接口")
    public CommonResult updateShowStatusOne(@PathVariable("id") Long id,Integer showStatus){
        int count = commentService.updateShowStatusOne(id, showStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/updateShowStatusBatch")
    @ApiOperation(value = "根据id批量修改显示状态",notes = "根据id批量修改显示状态接口")
    public CommonResult updateShowStatusBatch(@RequestParam("ids") List<Long> ids,Integer showStatus){
        int count = commentService.updateShowStatusBatch(ids, showStatus);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/item/{id}")
    @ApiOperation(value = "根据id获取评价详情",notes = "根据id去获取评价详情接口")
    public CommonResult getItem(@PathVariable("id") Long id){
        PmsComment item = commentService.getItem(id);
        return CommonResult.success(item);
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据商品名，商品分类，昵称获取评价分页",notes = "根据商品名，商品分类，昵称获取评价分页接口")
    public CommonResult<CommonPage<PmsComment>> list(@RequestParam(value = "productName",required = false) String productName,
                                                     @RequestParam(value = "memberNickName",required = false) String memberNickName,
                                                     @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<PmsComment> list = commentService.list(productName, memberNickName, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
