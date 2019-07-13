package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.entity.OmsOrderReturnReason;

import java.util.List;

/**
 *订单退货原因  service
 **/
public interface OmsOrderReturnReasonService {

    /**
     *添加退货原因
     * */
    int create(OmsOrderReturnReason orderReturnReason);

    /**
     *分页获取退货原因
     * */
    List<OmsOrderReturnReason> list(Integer pageSize,Integer pageNum);

    /**
     *根据id删除退货原因
     * */
    int delete(Long id);

    /**
     *根据id批量删除
      */
    int deleteAll(List<Long> ids);

    /**
     *根据id去修改退货原因
     * */
    int update(Long id,OmsOrderReturnReason orderReturnReason);

    /**
     *批量修改退货状态
     * */
    int updateStatus(List<Long> ids,Integer status);

    /**
     *根据id去修改退货状态
     * */
    int updateStatusOne(Long id,Integer status);

    /**
     *根据id去获取退货详情
     * */
    OmsOrderReturnReason getItem(Long id);
}
