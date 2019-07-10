package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.admin.dto.*;
import com.xiao.xiaomall.entity.OmsOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *订单管理service
 **/
public interface OmsOrderService {

    /**
     *订单查询
     * */
    List<OmsOrder> list(OmsOrderQueryParam queryParam,Integer pageSize,Integer pageNum);

    /**
     *批量发货
     * */
    @Transactional
    int delivery(List<OmsOrderdeliveryParam> deliveryParamList);

    /**
     * 批量关闭订单
     */
    int update(List<Long> ids,String note);

    /**
     * 批量删除订单
     * */
    int delete(List<Long> ids);

    /***
     *根据id去获取订单详情
     **/
    OmsOrderDetail detail(Long id);

    /**
     * 修改订单收货人信息
     * */
    int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);

    /**
     * 修改订单的费用信息
     * */
    @Transactional
    int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam);

    /**
     * 修改订单的备注信息
     * */
    int update(Long id,String note,Integer status);
}
