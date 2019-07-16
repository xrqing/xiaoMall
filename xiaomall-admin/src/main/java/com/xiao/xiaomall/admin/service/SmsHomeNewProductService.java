package com.xiao.xiaomall.admin.service;


import com.xiao.xiaomall.entity.SmsHomeNewProduct;
import java.util.List;

/**
 *新品推荐 service
 **/
public interface SmsHomeNewProductService {

    /**
     * 增加新品推荐
     * */
    int add(List<SmsHomeNewProduct> homeNewProductList);

    /**
     *根据id去删除新品推荐
     * */
    int delete(Long id);

    /**
     *根据id批量删除新品推荐
     * */
    int deleteBatch(List<Long> ids);

    /**
     *根据id去设置推荐状态
     * */
    int updateRecommendStatusOne(Long id, Integer recommendStatus);

    /**
     *根据id批量设置推荐状态
     * */
    int updateRecommendStatus(List<Long> ids,Integer recommendStatus);

    /**
     *根据id设置排序
     * */
    int updateSortOne(Long id,Integer sort);

    /**
     * 根据id批量设置排序
     * */
    int updateSort(List<Long> ids,Integer sort);

    /**
     *分页查询（根据新品名称和推荐状态）
     * */
    List<SmsHomeNewProduct> list(String productName,Integer recommendStatus,Integer pageSize,Integer pageNum);
}
