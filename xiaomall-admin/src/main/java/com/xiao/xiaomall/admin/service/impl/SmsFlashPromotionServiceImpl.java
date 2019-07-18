package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.service.SmsFlashPromotionService;
import com.xiao.xiaomall.entity.SmsFlashPromotion;
import com.xiao.xiaomall.entity.SmsFlashPromotionExample;
import com.xiao.xiaomall.mapper.SmsFlashPromotionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 *限时购物管理  serviceImpl
 **/
@Service
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {

    @Autowired
    private SmsFlashPromotionMapper flashPromotionMapper;

    /**
     *增加限时购物
     * */
    @Override
    public int add(SmsFlashPromotion flashPromotion) {
        flashPromotion.setCreateTime(new Date());
        int count = flashPromotionMapper.insert(flashPromotion);
        return count;
    }

    /**
     *根据id删除限时购物
     * */
    @Override
    public int delete(Long id) {
        int count = flashPromotionMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     *根据id修改上下线状态
     **/
    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();
        flashPromotion.setId(id);
        flashPromotion.setStatus(status);
        int count = flashPromotionMapper.updateByPrimaryKeySelective(flashPromotion);
        return count;
    }

    /**
     *根据id修改限时购物
     * */
    @Override
    public int update(Long id, SmsFlashPromotion flashPromotion) {
        flashPromotion.setId(id);
        int count = flashPromotionMapper.updateByPrimaryKeySelective(flashPromotion);
        return count;
    }

    /**
     *根据id获取限时购物详情
     * */
    @Override
    public SmsFlashPromotion getItem(Long id) {
        SmsFlashPromotion smsFlashPromotion = flashPromotionMapper.selectByPrimaryKey(id);
        return smsFlashPromotion;
    }

    /**
     *根据关键字获取分页查询
     * */
    @Override
    public List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        SmsFlashPromotionExample example = new SmsFlashPromotionExample();
        SmsFlashPromotionExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)){
            criteria.andTitleEqualTo("%"+keyword+"%");
        }
        List<SmsFlashPromotion> smsFlashPromotions = flashPromotionMapper.selectByExample(example);
        return smsFlashPromotions;
    }
}
