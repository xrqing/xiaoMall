package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.dao.SmsCouponDao;
import com.xiao.xiaomall.admin.dao.SmsCouponProductCategoryRelationDao;
import com.xiao.xiaomall.admin.dao.SmsCouponProductRelationDao;
import com.xiao.xiaomall.admin.dto.SmsCouponParam;
import com.xiao.xiaomall.admin.service.SmsCouponService;
import com.xiao.xiaomall.entity.*;
import com.xiao.xiaomall.mapper.SmsCouponMapper;
import com.xiao.xiaomall.mapper.SmsCouponProductCategoryRelationMapper;
import com.xiao.xiaomall.mapper.SmsCouponProductRelationMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.swing.StringUIClientPropertyKey;

import java.util.List;

/**
 *优惠卷管理  serviceImpl
 **/
@Service
public class SmsCouponServiceImpl implements SmsCouponService {

    @Autowired
    private SmsCouponMapper couponMapper;

    @Autowired
    private SmsCouponProductRelationDao couponProductRelationDao;

    @Autowired
    private SmsCouponProductCategoryRelationDao couponProductCategoryRelationDao;

    @Autowired
    private SmsCouponProductRelationMapper couponProductRelationMapper;

    @Autowired
    private SmsCouponProductCategoryRelationMapper couponProductCategoryRelationMapper;

    @Autowired
    private SmsCouponDao couponDao;

    /**
     *增加优惠卷
     * */
    @Override
    public int add(SmsCouponParam couponParam) {
        //1:插入优惠卷表操作
        couponParam.setPublishCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);
        int count = couponMapper.insert(couponParam);
        //2:插入优惠卷和商品的关系表
        if (couponParam.getUseType().equals(2)){
            for (SmsCouponProductRelation couponProductRelation : couponParam.getProductRelationList()) {
                couponProductRelation.setCouponId(couponParam.getId());
            }
            couponProductRelationDao.insertList(couponParam.getProductRelationList());
        }
        //3:插入优惠卷和商品分类表
        if (couponParam.getUseType().equals(1)){
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            couponProductCategoryRelationDao.insertList(couponParam.getProductCategoryRelationList());
        }
        return count;
    }

    /**
     * 根据id删除
     * */
    @Override
    public int delete(Long id) {
        //1:删除优惠卷主表id
        int count = couponMapper.deleteByPrimaryKey(id);
        //2:删除优惠卷和商品关系表id
        SmsCouponProductRelationExample couponProductRelationExample = new SmsCouponProductRelationExample();
        couponProductRelationExample.createCriteria().andCouponIdEqualTo(id);
        couponProductRelationMapper.deleteByExample(couponProductRelationExample);
        //3:删除优惠卷和商品分类关系表id
        SmsCouponProductCategoryRelationExample couponProductCategoryRelationExample = new SmsCouponProductCategoryRelationExample();
        couponProductCategoryRelationExample.createCriteria().andCouponIdEqualTo(id);
        couponProductCategoryRelationMapper.deleteByExample(couponProductCategoryRelationExample);
        return count;
    }

    /**
     *根据id获取优惠卷详情
     * */
    @Override
    public SmsCouponParam getItem(Long id) {
        return couponDao.getItem(id);
    }

    /**
     *根据id去更新优惠卷信息
     * */
    @Override
    public int update(Long id, SmsCouponParam couponParam) {
        //1:修改优惠卷信息
        couponParam.setId(id);
        int count = couponMapper.updateByPrimaryKeySelective(couponParam);
        //2：修改优惠卷和商品关系表的信息
        if (couponParam.getUseType().equals(2)){
            for (SmsCouponProductRelation couponProductRelation : couponParam.getProductRelationList()) {
                couponProductRelation.setCouponId(couponParam.getId());
            }
            SmsCouponProductRelationExample example = new SmsCouponProductRelationExample();
            example.createCriteria().andCouponIdEqualTo(id);
            couponProductRelationMapper.deleteByExample(example);
            couponProductRelationDao.insertList(couponParam.getProductRelationList());
        }
        //3：修改优惠卷和商品分类关系表的信息
        if (couponParam.getUseType().equals(1)){
            for (SmsCouponProductRelation couponProductRelation : couponParam.getProductRelationList()) {
                couponProductRelation.setCouponId(couponParam.getId());
            }
            SmsCouponProductCategoryRelationExample example = new SmsCouponProductCategoryRelationExample();
            example.createCriteria().andCouponIdEqualTo(id);
            couponProductCategoryRelationMapper.deleteByExample(example);
            couponProductCategoryRelationDao.insertList(couponParam.getProductCategoryRelationList());
        }
        return count;
    }

    /**
     *根据优惠卷的名称和类型分页获取
     * */
    @Override
    public List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        SmsCouponExample example = new SmsCouponExample();
        SmsCouponExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)){
            criteria.andNameEqualTo("%"+name+"%");
        }
        if (type!=null){
            criteria.andTypeEqualTo(type);
        }
        List<SmsCoupon> smsCoupons = couponMapper.selectByExample(example);
        return smsCoupons;
    }
}
