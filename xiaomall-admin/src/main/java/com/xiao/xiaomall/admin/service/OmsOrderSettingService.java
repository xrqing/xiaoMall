package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.entity.OmsOrderSetting;

/**
 *订单设置  service
 **/
public interface OmsOrderSettingService {

    /**
     *根据id去获取指定的订单设置
     * */
    OmsOrderSetting getItem(Long id);

    /**
     *修改指定订单设置
     * */
    int update(Long id,OmsOrderSetting orderSetting);
}
