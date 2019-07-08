package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.service.CmsPrefrenceAreaService;
import com.xiao.xiaomall.api.CommonResult;
import com.xiao.xiaomall.entity.CmsPrefrenceArea;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *商品优选  controller
 **/
@RestController
@RequestMapping("/prefrenceArea")
@Api(value ="商品优选" ,description = "商品优选接口")
public class CmsPrefrenceAreaController {

    @Autowired
    private CmsPrefrenceAreaService prefrenceAreaService;

    @GetMapping("/listAll")
    @ApiOperation(value = "获取所有的商品优选",notes = "获取所有的商品优选列表接口")
    public CommonResult<List<CmsPrefrenceArea>> listAll(){
        List<CmsPrefrenceArea> prefrenceAreaList = prefrenceAreaService.listAll();
        return CommonResult.success(prefrenceAreaList);
    }
}


