package com.xiao.xiaomall.admin.dto;


import com.xiao.xiaomall.entity.OmsCompanyAddress;
import com.xiao.xiaomall.entity.OmsOrderReturnApply;
import lombok.Getter;
import lombok.Setter;

/**
 * 申请信息封装
 */
public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {
    @Getter
    @Setter
    private OmsCompanyAddress companyAddress;
}
