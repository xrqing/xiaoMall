package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.admin.dto.PmsProductCategoryParam;
import com.xiao.xiaomall.admin.dto.PmsProductCategoryWithChildrenItem;
import com.xiao.xiaomall.entity.PmsProductCategory;
import io.swagger.models.auth.In;

import java.util.List;


/**
 *商品分类管理  service
 **/
public interface PmsProductCategoryService {

    /**
     * 增加商品分类
     */
    int add(PmsProductCategoryParam productCategoryParam);

    /**
     * 修改商品分类
     */
    int update(Long id, PmsProductCategoryParam pmsProductCategoryParam);

    /**
     * 分页获取商品类别
     */
    List<PmsProductCategory> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 根据id去获取商品分类
     */
    PmsProductCategory getItem(Long id);

    /**
     *根据id删除商品分类
     * */
    int delete(Long id);

    /**
     *批量修改导航栏显示状态
     * */
    int updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     *批量修改显示状态
     * */
    int updateShowStatus(List<Long> ids,Integer showStatus);

    /**
     *查询一级及子分类
     * */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
