package com.xiao.xiaomall.search.service;

import com.xiao.xiaomall.search.domain.EsProduct;
import com.xiao.xiaomall.search.domain.EsProductRelatedInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *商品搜索管理service
 **/
public interface EsProductService {

    /**
     * 从数据库中导入所有的商品到ES
     * */
    int importAll();

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsProduct> search(String keyword,Integer pageSize,Integer pageNum);

    /**
     *根据id去创建商品
     * */
    EsProduct create(Long id);

    /**
     * 根据id去删除商品
     * */
    void delete(Long id);

    /**
     * 批量删除商品
     * */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题复合查询
     */
    Page<EsProduct> search(String keyword,Long brandId,Long productCategoryId,Integer pageSize,Integer pageNum,Integer sort);

    /**
     * 根据商品的id推荐相关商品
     * */
    Page<EsProduct> recommend(Long id,Integer pageSize,Integer pageNum);

    /**
     * 获取搜索词相关的品牌，属性，分类
     * */
    EsProductRelatedInfo searchRelatedInfo(String keyword);
}

