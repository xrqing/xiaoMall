package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.service.SmsHomeRecommendProductService;
import com.xiao.xiaomall.entity.SmsHomeRecommendProduct;
import com.xiao.xiaomall.entity.SmsHomeRecommendProductExample;
import com.xiao.xiaomall.mapper.SmsHomeRecommendProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.print.Pageable;
import java.util.List;

/**
 *首页人气推荐 serviceImpl
 **/
@Service
public class SmsHomeRecommendProductImpl implements SmsHomeRecommendProductService {

    @Autowired
    private SmsHomeRecommendProductMapper homeRecommendProductMapper;

    /**
     *增加首页人气推荐
     * */
    @Override
    public int add(List<SmsHomeRecommendProduct> homeRecommendProductList) {
        for (SmsHomeRecommendProduct smsHomeRecommendProduct : homeRecommendProductList) {
            smsHomeRecommendProduct.setRecommendStatus(1);
            smsHomeRecommendProduct.setSort(0);
            homeRecommendProductMapper.insert(smsHomeRecommendProduct);
        }
        int size = homeRecommendProductList.size();
        return size;
    }

    /**
     *根据id删除人气推荐
     * */
    @Override
    public int deleteOne(Long id) {
        int count = homeRecommendProductMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     *根据id批量删除人气推荐
     * */
    @Override
    public int deleteBatch(List<Long> ids) {
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        example.createCriteria().andIdIn(ids);
        int count = homeRecommendProductMapper.deleteByExample(example);
        return count;
    }

    /**
     *根据id设置人气推荐状态
     * */
    @Override
    public int updateRecommendStatusOne(Long id, Integer recommendStatus) {
        SmsHomeRecommendProduct homeRecommendProduct = new SmsHomeRecommendProduct();
        homeRecommendProduct.setId(id);
        homeRecommendProduct.setRecommendStatus(recommendStatus);
        int count = homeRecommendProductMapper.updateByPrimaryKeySelective(homeRecommendProduct);
        return count;
    }

    /**
     *根据id批量设置人气推荐状态
     * */
    @Override
    public int updateRecommendStatusBatch(List<Long> ids, Integer recommendStatus) {
        SmsHomeRecommendProduct homeRecommendProduct = new SmsHomeRecommendProduct();
        homeRecommendProduct.setRecommendStatus(recommendStatus);
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        example.createCriteria().andIdIn(ids);
        int count = homeRecommendProductMapper.updateByExampleSelective(homeRecommendProduct, example);
        return count;
    }

    /**
     *根据id去设置排序
     * */
    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendProduct homeRecommendProduct = new SmsHomeRecommendProduct();
        homeRecommendProduct.setId(id);
        homeRecommendProduct.setSort(sort);
        int count = homeRecommendProductMapper.updateByPrimaryKeySelective(homeRecommendProduct);
        return count;
    }

    /**
     *根据推荐名称和推荐状态获取分页
     * */
    @Override
    public List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        SmsHomeRecommendProductExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(productName)){
            criteria.andProductNameEqualTo("%"+productName+"%");
        }
        if (!StringUtils.isEmpty(recommendStatus)){
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        List<SmsHomeRecommendProduct> smsHomeRecommendProducts = homeRecommendProductMapper.selectByExample(example);
        return smsHomeRecommendProducts;

    }
}
