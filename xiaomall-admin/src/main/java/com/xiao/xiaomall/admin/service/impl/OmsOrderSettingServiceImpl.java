package com.xiao.xiaomall.admin.service.impl;

import com.xiao.xiaomall.admin.service.OmsOrderSettingService;
import com.xiao.xiaomall.entity.OmsOrderSetting;
import com.xiao.xiaomall.mapper.OmsOrderSettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *订单设置 serviceImpl
 **/
@Service
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {

    @Autowired
    private OmsOrderSettingMapper orderSettingMapper;

    /**
     * 根据id去获取指定的订单设置
     * */
    @Override
    public OmsOrderSetting getItem(Long id) {
        return orderSettingMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id去修改指定的订单设置
     * */
    @Override
    public int update(Long id, OmsOrderSetting orderSetting) {
        orderSetting.setId(id);
        return orderSettingMapper.updateByPrimaryKey(orderSetting);
    }
}
