package com.xiao.xiaomall.admin.dto;

import lombok.Data;

/**
 *获取oss文件上传返回结果
 **/
@Data
public class OssPolicyResult {
    private String accessKeyId;
    private String policy;
    private String signature;
    private String dir;
    private String host;
}
