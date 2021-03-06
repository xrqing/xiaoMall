package com.xiao.xiaomall.admin.dto;

import com.xiao.xiaomall.entity.SmsFlashPromotionSession;
import lombok.Getter;
import lombok.Setter;

/**
 * 包含商品数量的场次信息
 **/
public class SmsFlashPromotionSessionDetail extends SmsFlashPromotionSession {

    @Setter
    @Getter
    private Integer productCount;
}
