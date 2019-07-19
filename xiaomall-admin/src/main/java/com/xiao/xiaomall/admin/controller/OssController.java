package com.xiao.xiaomall.admin.controller;

import com.xiao.xiaomall.admin.dto.OssCallbackResult;
import com.xiao.xiaomall.admin.dto.OssPolicyResult;
import com.xiao.xiaomall.admin.service.OssService;
import com.xiao.xiaomall.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *oss文件上传  controller
 **/
@RestController
@RequestMapping("/aliyun/oss")
@Api(value = "oss文件上传管理",description = "oss文件上传管理接口")
public class OssController {

    @Autowired
    private OssService ossService;

    @GetMapping("/policy")
    @ApiOperation(value = "oss上传签名生成",notes = "oss上传签名生成接口")
    public CommonResult<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policy();
        return CommonResult.success(result);
    }

    @PostMapping("/callback")
    @ApiOperation(value = "上传回调",notes = "上传回调接口")
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return CommonResult.success(ossCallbackResult);
    }
}
