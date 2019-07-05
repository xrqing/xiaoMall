package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.admin.dto.PmsProductAttributeParam;
import com.xiao.xiaomall.admin.dto.ProductAttrInfo;
import com.xiao.xiaomall.entity.PmsProductAttribute;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *商品属性管理service
 **/
public interface PmsProductAttributeService {

    /**
     * 分页查询商品属性
     * */
    List<PmsProductAttribute> list(Long productAttributeCategoryId,Integer type,Integer pageSize,Integer pageNum);

    /**
     *增加商品属性
     * */
    @Transactional
    int create(PmsProductAttributeParam pmsProductAttributeParam);

    /**
     *根据id修改商品属性
     * */
    int update(Long id,PmsProductAttributeParam productAttributeParam);

    /**
     *更据id查询商品属性
     * */
    PmsProductAttribute getItem(Long id);

    /**
     *批量删除
     * */
    @Transactional
    int delete(List<Long> ids);

    /**
     * 根据商品分类的id获取商品属性及属性分类
     * */
    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);
}
