package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.sun.corba.se.impl.resolver.ORBDefaultInitRefResolverImpl;
import com.xiao.xiaomall.admin.dao.OmsOrderDao;
import com.xiao.xiaomall.admin.dao.OmsOrderOperateHistoryDao;
import com.xiao.xiaomall.admin.dto.*;
import com.xiao.xiaomall.admin.service.OmsOrderService;
import com.xiao.xiaomall.entity.OmsOrder;
import com.xiao.xiaomall.entity.OmsOrderExample;
import com.xiao.xiaomall.entity.OmsOrderOperateHistory;
import com.xiao.xiaomall.mapper.OmsOrderMapper;
import com.xiao.xiaomall.mapper.OmsOrderOperateHistoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.ssl.HandshakeInStream;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *订单管理的实现  serviceImpl
 **/
@Service
public class OmsOrderServiceImpl implements OmsOrderService {

    @Autowired
    private OmsOrderMapper orderMapper;

    @Autowired
    private OmsOrderDao orderDao;

    @Autowired
    private OmsOrderOperateHistoryDao orderOperateHistoryDao;

    @Autowired
    private OmsOrderOperateHistoryMapper orderOperateHistoryMapper;

    /**
     * 查询订单
     * */
    @Override
    public List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        return orderDao.getList(queryParam);
    }

    /**
     *批量发货
     * */
    @Override
    public int delivery(List<OmsOrderdeliveryParam> deliveryParamList) {
        //批量发货
        int count = orderDao.delivery(deliveryParamList);
        //添加操作记录
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream().map(OmsOrderdeliveryParam -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setCreateTime(new Date());
            history.setNote("完成发货");
            history.setOperateMan("Test");
            history.setOrderStatus(2);
            history.setOrderId(OmsOrderdeliveryParam.getOrderId());
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryDao.insertList(operateHistoryList);
        return count;
    }

    /**
     * 批量修改订单
     * */
    @Override
    public int update(List<Long> ids, String note) {
        OmsOrder order = new OmsOrder();
        order.setNote(note);
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andIdIn(ids);
        return orderMapper.updateByExampleSelective(order,example);
    }

    /**
     *批量删除订单
     * */
    @Override
    public int delete(List<Long> ids) {
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andIdIn(ids);
        return orderMapper.deleteByExample(example);
    }

    /**
     * 根据id去获取指定的订单详情
     * */
    @Override
    public OmsOrderDetail detail(Long id) {
        return orderDao.getDetail(id);
    }

    /**
     * 修改收货人信息
     * */
    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        //修改修货信息
        OmsOrder order = new OmsOrder();
        order.setId(receiverInfoParam.getOrderId());
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
        order.setReceiverCity(receiverInfoParam.getReceiverCity());
        order.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
        order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
        order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
        order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //向记录表插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOperateMan("admin");
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        history.setCreateTime(new Date());
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    /**
     *修改订单费用信息
     * */
    @Override
    public int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) {
        //修改费用信息
        OmsOrder order = new OmsOrder();
        order.setId(moneyInfoParam.getOrderId());
        order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
        order.setFreightAmount(moneyInfoParam.getFreightAmount());
        order.setModifyTime(new Date());
        order.setStatus(moneyInfoParam.getStatus());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //向记录表插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(moneyInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("admin");
        history.setNote("修改订单的费用信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    /**
     *修改订单的备注信息
     * */
    @Override
    public int update(Long id, String note, Integer status) {
        //修改订单信息
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setStatus(status);
        order.setNote(note);
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //把修改记录加在记录表中
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setNote("修改订单备注信息："+note);
        history.setOperateMan("admin");
        history.setOrderStatus(status);
        history.setCreateTime(new Date());
        orderOperateHistoryMapper.insert(history);
        return count;
    }
}
