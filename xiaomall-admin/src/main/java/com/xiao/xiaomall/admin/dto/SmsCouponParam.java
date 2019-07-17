package com.xiao.xiaomall.admin.dto;

import com.xiao.xiaomall.entity.SmsCoupon;
import com.xiao.xiaomall.entity.SmsCouponProductCategoryRelation;
import com.xiao.xiaomall.entity.SmsCouponProductRelation;
import lombok.Data;

import java.util.List;

/**
 *优惠卷参数
 **/
@Data
public class SmsCouponParam extends SmsCoupon {
    private List<SmsCouponProductRelation> productRelationList;
    private List<SmsCouponProductCategoryRelation> productCategoryRelationList;
}
