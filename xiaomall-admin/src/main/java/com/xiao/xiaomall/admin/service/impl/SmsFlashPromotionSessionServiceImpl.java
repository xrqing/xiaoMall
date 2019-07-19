package com.xiao.xiaomall.admin.service.impl;


import com.xiao.xiaomall.admin.dto.SmsFlashPromotionSessionDetail;
import com.xiao.xiaomall.admin.service.SmsFlashPromotionProductRelationService;
import com.xiao.xiaomall.admin.service.SmsFlashPromotionSessionService;
import com.xiao.xiaomall.entity.SmsFlashPromotionSession;
import com.xiao.xiaomall.entity.SmsFlashPromotionSessionExample;
import com.xiao.xiaomall.mapper.SmsFlashPromotionSessionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.locale.provider.FallbackLocaleProviderAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *限时购场次管理 serviceImpl
 **/
@Service
public class SmsFlashPromotionSessionServiceImpl implements SmsFlashPromotionSessionService {

    @Autowired
    private SmsFlashPromotionSessionMapper flashPromotionSessionMapper;

    @Autowired
    private SmsFlashPromotionProductRelationService relationService;

    /**
     *增加限时购场次
     * */
    @Override
    public int add(SmsFlashPromotionSession flashPromotionSession) {
        flashPromotionSession.setCreateTime(new Date());
        int count = flashPromotionSessionMapper.insert(flashPromotionSession);
        return count;
    }

    /**
     *根据id删除限时购场次
     * */
    @Override
    public int delete(Long id) {
        int count = flashPromotionSessionMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     *根据id去修改限时购
     * */
    @Override
    public int update(Long id, SmsFlashPromotionSession flashPromotionSession) {
        flashPromotionSession.setId(id);
        int count = flashPromotionSessionMapper.updateByPrimaryKeySelective(flashPromotionSession);
        return count;
    }

    /**
     *根据id去获取具体限时购
     * */
    @Override
    public SmsFlashPromotionSession getItem(Long id) {
        SmsFlashPromotionSession smsFlashPromotionSession = flashPromotionSessionMapper.selectByPrimaryKey(id);
        return smsFlashPromotionSession;
    }

    /**
     *获取全部的限时购
     * */
    @Override
    public List<SmsFlashPromotionSession> list() {
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        List<SmsFlashPromotionSession> smsFlashPromotionSessions = flashPromotionSessionMapper.selectByExample(example);
        return smsFlashPromotionSessions;
    }

    /**
     *根据id去修改限时购状态
     * */
    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotionSession flashPromotionSession = new SmsFlashPromotionSession();
        flashPromotionSession.setId(id);
        flashPromotionSession.setStatus(status);
        int count = flashPromotionSessionMapper.updateByPrimaryKeySelective(flashPromotionSession);
        return count;
    }


    /**
     * 获取全部可选场次及其数量
     * */
    @Override
    public List<SmsFlashPromotionSessionDetail> selectList(Long flashPromotionId) {
        List<SmsFlashPromotionSessionDetail> result = new ArrayList<>();
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        example.createCriteria().andStatusEqualTo(1);
        List<SmsFlashPromotionSession> list = flashPromotionSessionMapper.selectByExample(example);
        for (SmsFlashPromotionSession flashPromotionSession : list) {
            SmsFlashPromotionSessionDetail detail = new SmsFlashPromotionSessionDetail();
            BeanUtils.copyProperties(flashPromotionSession,detail);
            int count = relationService.getCount(flashPromotionId, flashPromotionSession.getId());
            detail.setProductCount(count);
            result.add(detail);
        }
        return result;
    }
}
