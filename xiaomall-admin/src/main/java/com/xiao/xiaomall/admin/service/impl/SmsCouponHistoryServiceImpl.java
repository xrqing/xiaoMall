package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.service.SmsCouponHistoryService;
import com.xiao.xiaomall.entity.SmsCouponHistory;
import com.xiao.xiaomall.entity.SmsCouponHistoryExample;
import com.xiao.xiaomall.mapper.SmsCouponHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 *优惠卷领取记录管理  serviceImpl
 **/
@Service
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {

    @Autowired
    private SmsCouponHistoryMapper couponHistoryMapper;

    /**
     *分页获取领取记录
     * */
    @Override
    public List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        SmsCouponHistoryExample example = new SmsCouponHistoryExample();
        SmsCouponHistoryExample.Criteria criteria = example.createCriteria();
        if (couponId!=null){
            criteria.andCouponIdEqualTo(couponId);
        }
        if (useStatus!=null){
            criteria.andUseStatusEqualTo(useStatus);
        }
        if (orderSn!=null){
            criteria.andOrderSnEqualTo(orderSn);
        }
        List<SmsCouponHistory> smsCouponHistories = couponHistoryMapper.selectByExample(example);
        return smsCouponHistories;
    }
}
