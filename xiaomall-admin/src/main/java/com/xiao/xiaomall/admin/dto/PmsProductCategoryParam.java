package com.xiao.xiaomall.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 *商品分类传输对象
 **/
@Data
public class PmsProductCategoryParam {

    @ApiModelProperty("父类的编号")
    private Long parentId;

    @ApiModelProperty(value = "分类名称名称",required = true)
    private String name;

    @ApiModelProperty("分类的单位")
    private String productUnit;

    @ApiModelProperty("是否在导航栏显示")
    private Integer navStatus;

    @ApiModelProperty("是否显示")
    private Integer showStatus;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("产品相关筛选属性集合")
    private List<Long> productAttributeIdList;
}
