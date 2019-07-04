package com.xiao.xiaomall.admin.dao;

import com.xiao.xiaomall.admin.dto.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 *商品分类自定义dao
 **/
public interface PmsProductCategoryDao {
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
