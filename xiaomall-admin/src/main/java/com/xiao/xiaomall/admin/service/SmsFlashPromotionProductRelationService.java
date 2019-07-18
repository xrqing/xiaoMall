package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.admin.dto.SmsFlashPromotionProduct;
import com.xiao.xiaomall.entity.SmsFlashPromotionProductRelation;

import java.util.List;

/**
 *限时购和商品关系管理  service
 **/
public interface SmsFlashPromotionProductRelationService {

    /**
     *增加关联
     * */
    int add(List<SmsFlashPromotionProductRelation> flashPromotionProductRelationList);

    /**
     * 根据id去删除
     * */
    int delete(Long id);

    /**
     *根据id去修改关联
     * */
    int update(Long id,SmsFlashPromotionProductRelation relation);

    /**
     * 分页查询相关商品及促销信息
     * */
    List<SmsFlashPromotionProduct> list(Long flashPromotionId,Long flashPromotionSessionId,Integer pageSize,Integer pageNum);

    /**
     *根据活动和场次id获取商品关系数量
     * */
    int getCount(Long flashPromotionId,Long flashPromotionSessionId);
}
