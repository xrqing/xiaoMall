package com.xiao.xiaomall.protal.domain;

import com.xiao.xiaomall.entity.CmsSubject;
import com.xiao.xiaomall.entity.PmsBrand;
import com.xiao.xiaomall.entity.PmsProduct;
import com.xiao.xiaomall.entity.SmsHomeAdvertise;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 *首页返回信息的封装
 **/
@Data
public class HomeContentResult {
    @ApiModelProperty(value = "轮播广告")
    private List<SmsHomeAdvertise> homeAdvertiseList;

    @ApiModelProperty(value = "推荐品牌")
    private List<PmsBrand> brandList;

    @ApiModelProperty(value = "秒杀信息的封装")
    private HomeFlashPromotion homeFlashPromotion;

    @ApiModelProperty(value = "新品推荐")
    private List<PmsProduct> newProductList;

    @ApiModelProperty(value = "人气推荐")
    private List<PmsProduct> hotProductList;

    @ApiModelProperty(value = "推荐专题")
    private List<CmsSubject> subjectList;
}
