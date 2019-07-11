package com.xiao.xiaomall.admin.dao;

import com.xiao.xiaomall.admin.dto.OmsOrderReturnApplyResult;
import com.xiao.xiaomall.admin.dto.OmsReturnApplyQueryParam;
import com.xiao.xiaomall.entity.OmsOrderReturnApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *订单退货自定义dao
 **/
public interface OmsOrderReturnApplyDao {

    /**
     *分页查询订单退货
     * */
    List<OmsOrderReturnApply> getList(@Param("queryParam") OmsReturnApplyQueryParam orderReturnApplyParam);

    /**
     * 获取申请详情
     */
    OmsOrderReturnApplyResult getDetail(@Param("id")Long id);
}
