package com.xiao.xiaomall.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 品牌推荐
 * */
@Data
@ToString
public class SmsHomeBrand implements Serializable {
    private Long id;

    private Long brandId;

    private String brandName;

    @ApiModelProperty(value = "0 -> 不推荐，1 ->推荐")
    private Integer recommendStatus;

    private Integer sort;

    private static final long serialVersionUID = 1L;

}