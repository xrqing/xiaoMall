package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.entity.SmsHomeRecommendProduct;

import java.util.List;

/**
 *首页人气推荐  service
 **/
public interface SmsHomeRecommendProductService {

    /**
     *添加首页人气推荐
     * */
    int add(List<SmsHomeRecommendProduct> homeRecommendProductList);

    /**
     *根据id删除人气推荐
     * */
    int deleteOne(Long id);

    /**
     *根据id批量删除人气推荐
     * */
    int deleteBatch(List<Long> ids);

    /**
     *根据ids设置人气推荐状态
     * */
    int updateRecommendStatusOne(Long id,Integer recommendStatus);

    /**
     *根据id批量设置人气推荐状态
     * */
    int updateRecommendStatusBatch(List<Long> ids,Integer recommendStatus);

    /**
     *根据id去设置排序
     * */
    int updateSort(Long id,Integer sort);

    /**
     *根据推荐名称和推荐状态获取分页
     * */
    List<SmsHomeRecommendProduct> list(String productName,Integer recommendStatus,Integer pageSize,Integer pageNum);
}
