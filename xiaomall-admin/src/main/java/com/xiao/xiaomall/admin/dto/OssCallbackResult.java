package com.xiao.xiaomall.admin.dto;

import lombok.Data;

/**
 *oss文件上传返回结果
 **/
@Data
public class OssCallbackResult {
    private String filename;
    private String size;
    private String mimeType;
    private String width;
    private String height;
}
