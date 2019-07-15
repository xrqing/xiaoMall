package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.entity.SmsHomeBrand;

import java.util.List;

/**
 *品牌推荐 service
 **/

public interface SmsHomeBrandService {

    /**
     *添加品牌
     * */
    int add(List<SmsHomeBrand > homeBrandList);

    /**
     * 根据id删除品牌推荐
     * */
    int delete(Long id);

    /**
     *根据id批量删除品牌推荐
     * */
    int deleteAll(List<Long> ids);

    /**
     *根据id设置品牌推荐
     * */
    int updateRecommendStatus(Long id,Integer recommendStatus);

    /**
     * 根据id批量设置推荐状态 0->不推荐，1->推荐
     * */
    int recommendStatus(List<Long> ids,Integer recommendStatus);

    /**
     * 根据id去修改排序
     * */
    int updateSort(Long id,Integer sort);

    /**
     *分页查询
     * */
    List<SmsHomeBrand> list(String brandName,Integer recommendStatus,Integer pageSize,Integer pageNum);
}
