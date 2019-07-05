package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.service.CmsSubjectService;
import com.xiao.xiaomall.api.CommonPage;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.CmsSubject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *商品专题管理 controller
 **/
@RestController
@RequestMapping("/cmsSubject")
@Api(value = "商品专题管理",description = "商品专题管理接口")
public class CmsSubjectController {

    @Autowired
    private CmsSubjectService subjectService;

    @GetMapping("/listAll")
    @ApiOperation(value = "查询所有商品专题",notes = "查询所有商品专题接口")
    public CommonResult<List<CmsSubject>> list(){
        List<CmsSubject> listAll = subjectService.listAll();
        return CommonResult.success(listAll);
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据关键字分页查询所有商品专题",notes = "根据关键字分页查询所有商品专题接口")
    public CommonResult<CommonPage<CmsSubject>> list(@RequestParam(value = "keyword",required = false) String keyword,
                                                     @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<CmsSubject> list = subjectService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
