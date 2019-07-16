package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.service.SmsHomeNewProductService;
import com.xiao.xiaomall.entity.SmsHomeNewProduct;
import com.xiao.xiaomall.entity.SmsHomeNewProductExample;
import com.xiao.xiaomall.mapper.SmsHomeNewProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 *新品推荐  serviceImpl
 **/
@Service
public class SmsHomeNewProductServiceImpl implements SmsHomeNewProductService {

    @Autowired
    private SmsHomeNewProductMapper homeNewProductMapper;

    /**
     * 增加新品推荐
     * */
    @Override
    public int add(List<SmsHomeNewProduct> homeNewProductList) {
        for (SmsHomeNewProduct homeNewProduct : homeNewProductList) {
            homeNewProduct.setRecommendStatus(1);
            homeNewProduct.setSort(0);
            homeNewProductMapper.insert(homeNewProduct);
        }
        int size = homeNewProductList.size();
        return size;
    }

    /**
     *根据id去删除新品推荐
     * */
    @Override
    public int delete(Long id) {
        int count = homeNewProductMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     *根据id去批量删除新品推荐
     * */
    @Override
    public int deleteBatch(List<Long> ids) {
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        example.createCriteria().andIdIn(ids);
        int count = homeNewProductMapper.deleteByExample(example);
        return count;
    }

    /**
     *根据id去设置推荐状态
     * */
    @Override
    public int updateRecommendStatusOne(Long id, Integer recommendStatus) {
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setRecommendStatus(recommendStatus);
        int count = homeNewProductMapper.updateByPrimaryKeySelective(homeNewProduct);
        return count;
    }

    /**
     *根据id批量设置推荐状态
     * */
    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setRecommendStatus(recommendStatus);
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        example.createCriteria().andIdIn(ids);
        int count = homeNewProductMapper.updateByExampleSelective(homeNewProduct, example);
        return count;
    }

    /**
     * 根据id设置排序
     * */
    @Override
    public int updateSortOne(Long id, Integer sort) {
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        int count = homeNewProductMapper.updateByPrimaryKeySelective(homeNewProduct);
        return count;
    }

    /**
     * 根据id批量设置排序
     * */
    @Override
    public int updateSort(List<Long> ids, Integer sort) {
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setSort(sort);
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        example.createCriteria().andIdIn(ids);
        int count = homeNewProductMapper.updateByExampleSelective(homeNewProduct, example);
        return count;
    }

    /**
     *分页查询（根据新品名称和推荐状态）
     * */
    @Override
    public List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        SmsHomeNewProductExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(productName)){
            criteria.andProductNameEqualTo("%"+productName+"%");
        }
        if (!StringUtils.isEmpty(recommendStatus)){
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        List<SmsHomeNewProduct> smsHomeNewProducts = homeNewProductMapper.selectByExample(example);
        return smsHomeNewProducts;
    }
}
