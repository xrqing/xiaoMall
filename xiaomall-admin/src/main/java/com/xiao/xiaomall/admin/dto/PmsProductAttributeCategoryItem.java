package com.xiao.xiaomall.admin.dto;


import com.xiao.xiaomall.entity.PmsProductAttribute;
import com.xiao.xiaomall.entity.PmsProductAttributeCategory;
import lombok.Data;

import java.util.List;

/**
 * 包含有分类下属性的dto
 */
@Data
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    private List<PmsProductAttribute> productAttributeList;
}
