package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.dto.PmsBrandParam;
import com.xiao.xiaomall.admin.service.PmsBrandService;
import com.xiao.xiaomall.entity.PmsBrand;
import com.xiao.xiaomall.entity.PmsBrandExample;
import com.xiao.xiaomall.entity.PmsProduct;
import com.xiao.xiaomall.entity.PmsProductExample;
import com.xiao.xiaomall.mapper.PmsBrandMapper;
import com.xiao.xiaomall.mapper.PmsProductMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 商品品牌 的实现
 * */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;

    @Autowired
    private PmsProductMapper productMapper;

    /**
     * 查询全部的商品品牌
     * */
    @Override
    public List<PmsBrand> listAllBrand() {
        PmsBrandExample example = new PmsBrandExample();
        return brandMapper.selectByExample(example);
    }

    /**
     * 添加商品品牌
     * */
    @Override
    public int createBrand(PmsBrandParam brandParam) {
        PmsBrand brand = new PmsBrand();
        BeanUtils.copyProperties(brandParam,brand);
        if (StringUtils.isEmpty(brand.getFirstLetter())){
            brand.setFirstLetter(brand.getName().substring(0,1));
        }
        return brandMapper.insertSelective(brand);
    }

    /**
     *修改商品品牌
     * */
    @Override
    public int updateBrand(Long id, PmsBrandParam brandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(brandParam,pmsBrand);
        pmsBrand.setId(id);
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())){
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0,1));
        }
        PmsProduct product = new PmsProduct();
        product.setBrandName(pmsBrand.getName());
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andBrandIdEqualTo(id);
        productMapper.updateByExampleSelective(product,example);
        return brandMapper.updateByPrimaryKeySelective(pmsBrand);
    }

    /**
     * 删除商品品牌
     * */
    @Override
    public int delete(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除商品品牌
     * */
    @Override
    public int deleteAll(List<Long> ids) {
        PmsBrandExample example = new PmsBrandExample();
        example.createCriteria().andIdIn(ids);
        return brandMapper.deleteByExample(example);
    }

    /**
     *分页获取商品品牌
     * */
    @Override
    public List<PmsBrand> list(String keyword, int pageSize, int pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        PmsBrandExample example = new PmsBrandExample();
        example.setOrderByClause("sort desc");
        PmsBrandExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)){
            criteria.andNameLike("%"+keyword+"%");
        }
        return brandMapper.selectByExample(example);
    }

    /**
     *查询单个商品品牌
     * */
    @Override
    public PmsBrand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     *批量修改显示状态
     * */
    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsBrand brand = new PmsBrand();
        brand.setShowStatus(showStatus);
        PmsBrandExample example = new PmsBrandExample();
        example.createCriteria().andIdIn(ids);
        return brandMapper.updateByExampleSelective(brand,example);
    }

    /**
     *批量修改制造商状态
     * */
    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        PmsBrand brand = new PmsBrand();
        brand.setFactoryStatus(factoryStatus);
        PmsBrandExample example = new PmsBrandExample();
        example.createCriteria().andIdIn(ids);
        return brandMapper.updateByExampleSelective(brand,example);
    }


}
