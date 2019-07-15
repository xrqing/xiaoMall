package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.service.SmsHomeBrandService;
import com.xiao.xiaomall.entity.SmsHomeBrand;
import com.xiao.xiaomall.entity.SmsHomeBrandExample;
import com.xiao.xiaomall.mapper.SmsHomeBrandMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 *推荐品牌 serviceImpl
 **/
@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {

    @Autowired
    private SmsHomeBrandMapper homeBrandMapper;

    /**
     *添加品牌推荐
     * */
    @Override
    public int add(List<SmsHomeBrand > homeBrandList) {
        for (SmsHomeBrand smsHomeBrand : homeBrandList) {
            smsHomeBrand.setRecommendStatus(1);//设置默认为推荐状态
            smsHomeBrand.setSort(0);//设置排序为0 ，排在最后
            homeBrandMapper.insert(smsHomeBrand);
        }
        int size = homeBrandList.size();
        return size;
    }

    /**
     * 根据id删除品牌推荐
     * */
    @Override
    public int delete(Long id) {
        int count = homeBrandMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 根据id批量删除品牌推荐
     * */
    @Override
    public int deleteAll(List<Long> ids) {
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        example.createCriteria().andIdIn(ids);
        int count = homeBrandMapper.deleteByExample(example);
        return count;
    }

    /**
     *根据id设置品牌推荐 0->不推荐，1->推荐
     * */
    @Override
    public int updateRecommendStatus(Long id, Integer recommendStatus) {
        SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setId(id);
        homeBrand.setRecommendStatus(recommendStatus);
        int count = homeBrandMapper.updateByPrimaryKeySelective(homeBrand);
        return count;
    }

    /**
     *批量设置推荐状态 0->不推荐，1->推荐
     * */
    @Override
    public int recommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setRecommendStatus(recommendStatus);
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        example.createCriteria().andIdIn(ids);
        int count = homeBrandMapper.updateByExampleSelective(homeBrand, example);
        return count;
    }

    /**
     * 根据id去修改排序
     * */
    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeBrand brand = new SmsHomeBrand();
        brand.setId(id);
        brand.setSort(sort);
        int count = homeBrandMapper.updateByPrimaryKeySelective(brand);
        return count;
    }

    /**
     *分页查询
     * */
    @Override
    public List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        SmsHomeBrandExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(brandName)){
            criteria.andBrandNameEqualTo("%"+brandName+"%");
        }
        if (!StringUtils.isEmpty(recommendStatus)){
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        List<SmsHomeBrand> smsHomeBrand = homeBrandMapper.selectByExample(example);
        return smsHomeBrand;
    }
}
