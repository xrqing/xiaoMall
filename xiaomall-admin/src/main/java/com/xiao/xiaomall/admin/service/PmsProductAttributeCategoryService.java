package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.admin.dto.PmsProductAttributeCategoryItem;
import com.xiao.xiaomall.entity.PmsProductAttributeCategory;

import java.util.List;

/**
 *商品属性分类管理 service
 **/
public interface PmsProductAttributeCategoryService {

    /**
     *根据名称添加商品属性分类
     * */
    int add(String name);

    /**
     *根据id修改商品分类属性
     * */
    int update(Long id,String name);

    /**
     * 根据id删除商品分类属性
     * */
    int delete(Long id);

    /**
     * 根据id查询商品分类属性
     * */
    PmsProductAttributeCategory getItem(Long id);

    /**
     * 分页查询商品分类属性
     * */
    List<PmsProductAttributeCategory> list(Integer pageSize,Integer pageNum);

    /**
     * 获取所有商品属性分类及其下属性
     * */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
