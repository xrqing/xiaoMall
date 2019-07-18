package com.xiao.xiaomall.admin.dto;


import com.xiao.xiaomall.entity.PmsProduct;
import com.xiao.xiaomall.entity.SmsFlashPromotionProductRelation;
import lombok.Getter;
import lombok.Setter;

/**
 * 限时购及商品信息封装
 */
public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation {
    @Getter
    @Setter
    private PmsProduct product;
}
