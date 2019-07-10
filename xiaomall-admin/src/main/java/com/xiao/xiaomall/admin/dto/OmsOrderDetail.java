package com.xiao.xiaomall.admin.dto;

import com.xiao.xiaomall.entity.OmsOrder;
import com.xiao.xiaomall.entity.OmsOrderItem;
import com.xiao.xiaomall.entity.OmsOrderOperateHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单详情信息参数
 */
public class OmsOrderDetail extends OmsOrder {
    @Getter
    @Setter
    private List<OmsOrderItem> orderItemList;
    @Getter
    @Setter
    private List<OmsOrderOperateHistory> historyList;
}
