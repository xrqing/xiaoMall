package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.admin.dto.SmsCouponParam;
import com.xiao.xiaomall.entity.SmsCoupon;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 *优惠卷管理  service
 **/
public interface SmsCouponService {

    /**
     *增加优惠卷
     * */
    @Transactional
    int add(SmsCouponParam couponParam);

    /**
     *根据id删除
     * */
    int delete(Long id);

    /**
     *根据id获取优惠卷详情
     * */
    SmsCouponParam getItem(Long id);

    /**
     *根据id去更新优惠卷信息
     * */
    @Transactional
    int update(Long id,SmsCouponParam couponParam);

    /**
     *根据优惠卷的名称和类型分页获取
     * */
    List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum);
}
