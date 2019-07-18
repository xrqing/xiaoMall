package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.entity.SmsFlashPromotion;

import java.util.List;

/**
 *限时购物管理 service
 **/
public interface SmsFlashPromotionService {

    /**
     *增加限时购物
     * */
    int add(SmsFlashPromotion flashPromotion);

    /**
     * 根据id删除限时购物
     * */
    int delete(Long id);

    /**
     *根据id修改上下线状态
     * */
    int updateStatus(Long id,Integer status);

    /**
     *根据id修改限时购物
     * */
    int update(Long id,SmsFlashPromotion flashPromotion);

    /**
     *根据id获取限时购物详情
     * */
    SmsFlashPromotion getItem(Long id);

    /**
     *根据关键字获取分页查询
     * */
    List<SmsFlashPromotion> list(String keyword,Integer pageSize,Integer pageNum);
}
