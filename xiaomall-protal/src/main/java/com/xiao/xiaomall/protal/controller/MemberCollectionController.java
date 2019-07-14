package com.xiao.xiaomall.protal.controller;

import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.protal.domain.MemberProductCollection;
import com.xiao.xiaomall.protal.service.MemberCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *会员的收藏  controller
 **/
@RestController
@RequestMapping("/memberCollection")
@Api(value = "会员的收藏",description = "会员的收藏接口")
public class MemberCollectionController {

    @Autowired
    private MemberCollectionService collectionService;

    @PostMapping("/add")
    @ApiOperation(value = "会员的收藏",notes = "增加会员收藏接口")
    public CommonResult add(@RequestBody MemberProductCollection productCollection){
        int count = collectionService.add(productCollection);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除收藏",notes = "删除收藏接口")
    public CommonResult delete(Long memberId,long productId){
        int count = collectionService.deleteProduct(memberId, productId);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/list/{memberId}")
    @ApiOperation(value = "查询",notes = "查询接口")
    public CommonResult<List<MemberProductCollection>> list(@PathVariable("memberId") Long memberId){
        List<MemberProductCollection> list = collectionService.list(memberId);
        return CommonResult.success(list);
    }
}
