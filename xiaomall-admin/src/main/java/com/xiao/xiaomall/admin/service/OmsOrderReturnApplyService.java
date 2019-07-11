package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.admin.dto.OmsOrderReturnApplyResult;
import com.xiao.xiaomall.admin.dto.OmsReturnApplyQueryParam;
import com.xiao.xiaomall.admin.dto.OmsUpdateStatusParam;
import com.xiao.xiaomall.entity.OmsOrderReturnApply;
import java.util.List;

/**
 *订单退货处理  service
 **/
public interface OmsOrderReturnApplyService {

    /**
     *查询退货处理
     * */
    List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     *批量删除
     * */
    int delete(List<Long> ids);

    /**
     *根据id去查询退货订单
     * */
    OmsOrderReturnApplyResult getItem(Long id);

    /**
     *根据id去修改申请状态(确认和拒绝退货)
     * */
    int updateStatus(Long id, OmsUpdateStatusParam param);
}
