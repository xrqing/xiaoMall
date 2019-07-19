package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.admin.dto.SmsFlashPromotionSessionDetail;
import com.xiao.xiaomall.entity.SmsFlashPromotionSession;

import java.util.List;

/**
 *限时购场次管理 service
 **/
public interface SmsFlashPromotionSessionService {

    /**
     * 增加限时购场次
     * */
    int add(SmsFlashPromotionSession flashPromotionSession);

    /**
     *根据id删除限时购场次
     * */
    int delete(Long id);

    /**
     *根据id编辑限时购场次
     * */
    int update(Long id,SmsFlashPromotionSession flashPromotionSession);

    /**
     *根据id获取指定场次购详情
     * */
    SmsFlashPromotionSession getItem(Long id);

    /**
     * 获取全部的场次购
     * */
    List<SmsFlashPromotionSession> list();

    /**
     *根据id去修改启用状态
     * */
    int updateStatus(Long id,Integer status);

    /**
     * 获取全部可选场次及其数量
     * */
    List<SmsFlashPromotionSessionDetail> selectList(Long flashPromotionId);
}
