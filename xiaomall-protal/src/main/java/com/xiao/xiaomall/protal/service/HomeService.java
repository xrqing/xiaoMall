package com.xiao.xiaomall.protal.service;
import com.xiao.xiaomall.entity.CmsSubject;
import com.xiao.xiaomall.entity.PmsProduct;
import com.xiao.xiaomall.entity.PmsProductCategory;
import com.xiao.xiaomall.protal.domain.HomeContentResult;
import java.util.List;

/**
 *首页内容管理 service
 **/
public interface HomeService {

    /**
     * 获取首页内容
     * */
    HomeContentResult content();

    /**
     *首页商品推荐
     * */
    List<PmsProduct> recommendProductList(Integer pageSize,Integer pageNum);

    /**
     * 获取商品的分类
     * */
    List<PmsProductCategory> getProductCateList(Long parentId);

    /**
     * 根据专题分类分页获取专题
     * */
    List<CmsSubject> getSubjectList(Long categoryId,Integer pageSize,Integer pageNum);
}
