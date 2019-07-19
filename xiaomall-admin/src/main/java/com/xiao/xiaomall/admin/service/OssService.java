package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.admin.dto.OssCallbackResult;
import com.xiao.xiaomall.admin.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 *oss文件上传管理  service
 **/
public interface OssService {

    /**
     * 签名生成
     * */
    OssPolicyResult policy();

    /**
     *上传回调
     * */
    OssCallbackResult callback(HttpServletRequest request);
}
