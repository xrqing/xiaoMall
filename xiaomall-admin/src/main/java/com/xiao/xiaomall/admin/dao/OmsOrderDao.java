package com.xiao.xiaomall.admin.dao;

import com.xiao.xiaomall.admin.dto.OmsOrderDetail;
import com.xiao.xiaomall.admin.dto.OmsOrderQueryParam;
import com.xiao.xiaomall.admin.dto.OmsOrderdeliveryParam;
import com.xiao.xiaomall.entity.OmsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *订单自定义dao
 **/
public interface OmsOrderDao {

    /**
     *条件查询订单
     * */
    List<OmsOrder> getList(@Param("queryParam")OmsOrderQueryParam queryParam);

    /**
     *批量发货
     * */
    int delivery(@Param("list") List<OmsOrderdeliveryParam> deliveryParamList);

    /**
     *根据id去获取订单详情
     * */
    OmsOrderDetail getDetail(@Param("id") Long id);
}
