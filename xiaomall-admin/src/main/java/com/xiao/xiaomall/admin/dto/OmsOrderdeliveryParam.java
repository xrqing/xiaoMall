package com.xiao.xiaomall.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *订单发货参数
 **/
@Data
public class OmsOrderdeliveryParam {

    @ApiModelProperty("订单的id")
    private Long orderId;

    @ApiModelProperty("物流公司")
    private String deliveryCompany;

    @ApiModelProperty("物流单号")
    private String deliverySn;
}
