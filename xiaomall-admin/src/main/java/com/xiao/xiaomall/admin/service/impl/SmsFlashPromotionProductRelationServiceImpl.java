package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.dao.SmsFlashPromotionProductRelationDao;
import com.xiao.xiaomall.admin.dto.SmsFlashPromotionProduct;
import com.xiao.xiaomall.admin.service.SmsFlashPromotionProductRelationService;
import com.xiao.xiaomall.entity.SmsFlashPromotionProductRelation;
import com.xiao.xiaomall.entity.SmsFlashPromotionProductRelationExample;
import com.xiao.xiaomall.mapper.SmsFlashPromotionProductRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *限时购和商品关系管理  serviceImpl
 **/
@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {

    @Autowired
    private SmsFlashPromotionProductRelationMapper relationMapper;

    @Autowired
    private SmsFlashPromotionProductRelationDao relationDao;

    /**
     *增加关联rrr
     * */
    @Override
    public int add(List<SmsFlashPromotionProductRelation> flashPromotionProductRelationList) {
        for (SmsFlashPromotionProductRelation flashPromotionProductRelation : flashPromotionProductRelationList) {
            relationMapper.insert(flashPromotionProductRelation);
        }
        int size = flashPromotionProductRelationList.size();
        return size;
    }

    /**
     * 根据id删除关联
     * */
    @Override
    public int delete(Long id) {
        int count = relationMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     *根据id修改关联
     * */
    @Override
    public int update(Long id, SmsFlashPromotionProductRelation relation) {
        relation.setId(id);
        int count = relationMapper.updateByPrimaryKeySelective(relation);
        return count;
    }

    /**
     * 分页查询相关商品及促销信息
     * */
    @Override
    public List<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        return relationDao.getList(flashPromotionId, flashPromotionSessionId);
    }

    /**
     *根据活动和场次id获取商品关系数量
     * */
    @Override
    public int getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        SmsFlashPromotionProductRelationExample example = new SmsFlashPromotionProductRelationExample();
        example.createCriteria().andFlashPromotionIdEqualTo(flashPromotionId).andFlashPromotionSessionIdEqualTo(flashPromotionSessionId);
        int count = relationMapper.countByExample(example);
        return count;
    }
}
