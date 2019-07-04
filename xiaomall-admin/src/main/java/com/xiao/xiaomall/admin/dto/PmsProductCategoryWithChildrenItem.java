package com.xiao.xiaomall.admin.dto;

import com.xiao.xiaomall.entity.PmsProductCategory;
import lombok.Data;

import java.util.List;

/**
 *查询所有一级及子级分类
 **/
@Data
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {

    private List<PmsProductCategory> children;
}
