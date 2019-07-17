package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.entity.SmsCouponHistory;

import java.util.List;

/**
 *优惠卷领取记录管理 service
 **/
public interface SmsCouponHistoryService {

    /**
     *分页获取领取记录
     * */
    List<SmsCouponHistory> list(Long couponId,Integer useStatus,String orderSn,Integer pageSize,Integer pageNum);
}
