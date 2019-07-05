package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.dao.PmsProductAttributeDao;
import com.xiao.xiaomall.admin.dto.PmsProductAttributeParam;
import com.xiao.xiaomall.admin.dto.ProductAttrInfo;
import com.xiao.xiaomall.admin.service.PmsProductAttributeService;
import com.xiao.xiaomall.entity.PmsProductAttribute;
import com.xiao.xiaomall.entity.PmsProductAttributeCategory;
import com.xiao.xiaomall.entity.PmsProductAttributeExample;
import com.xiao.xiaomall.mapper.PmsProductAttributeCategoryMapper;
import com.xiao.xiaomall.mapper.PmsProductAttributeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *商品属性管理 service的实现
 **/
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {

    @Autowired
    private PmsProductAttributeMapper productAttributeMapper;

    @Autowired
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Autowired
    private PmsProductAttributeDao productAttributeDao;

    /**
     * 分页查询商品属性
     * */
    @Override
    public List<PmsProductAttribute> list(Long productAttributeCategoryId, Integer type, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andProductAttributeCategoryIdEqualTo(productAttributeCategoryId).andTypeEqualTo(type);
        return productAttributeMapper.selectByExample(example);
    }

    /**
     * 增加商品属性
     * */
    @Override
    public int create(PmsProductAttributeParam pmsProductAttributeParam) {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(pmsProductAttributeParam, pmsProductAttribute);
        int count = productAttributeMapper.insertSelective(pmsProductAttribute);
        //新增商品属性以后需要更新商品属性分类数量
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(pmsProductAttribute.getProductAttributeCategoryId());
        if(pmsProductAttribute.getType()==0){
            pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()+1);
        }else if(pmsProductAttribute.getType()==1){
            pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()+1);
        }
        productAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
        return count;
    }

    /**
     *根据id修改商品属性
     * */
    @Override
    public int update(Long id, PmsProductAttributeParam productAttributeParam) {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        pmsProductAttribute.setId(id);
        BeanUtils.copyProperties(productAttributeParam,pmsProductAttribute);
        return productAttributeMapper.updateByPrimaryKeySelective(pmsProductAttribute);
    }

    /**
     *根据id查询商品属性
     * */
    @Override
    public PmsProductAttribute getItem(Long id) {
        return productAttributeMapper.selectByPrimaryKey(id);
    }


    /**
     *批量删除
     * */
    @Override
    public int delete(List<Long> ids) {
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        example.createCriteria().andIdIn(ids);
        return productAttributeMapper.deleteByExample(example);
    }

    /**
     *根据商品分类的id获取商品属性及属性分类
     * */
    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        return productAttributeDao.getProductAttrInfo(productCategoryId);
    }
}
