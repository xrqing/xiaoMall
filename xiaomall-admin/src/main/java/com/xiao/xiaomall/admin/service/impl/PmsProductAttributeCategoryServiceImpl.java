package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.dao.PmsProductAttributeCategoryDao;
import com.xiao.xiaomall.admin.dto.PmsProductAttributeCategoryItem;
import com.xiao.xiaomall.admin.service.PmsProductAttributeCategoryService;
import com.xiao.xiaomall.entity.PmsProductAttributeCategory;
import com.xiao.xiaomall.entity.PmsProductAttributeCategoryExample;
import com.xiao.xiaomall.mapper.PmsProductAttributeCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *商品属性分类管理 serviceImpl
 **/
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {

    @Autowired
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Autowired
    private PmsProductAttributeCategoryDao productAttributeCategoryDao;

    /**
     *根据名称添加商品属性分类
     * */
    @Override
    public int add(String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        return productAttributeCategoryMapper.insertSelective(productAttributeCategory);
    }

    /**
     *根据id去修改商品分类属性
     * */
    @Override
    public int update(Long id,String name) {
        PmsProductAttributeCategory pmsProductAttributeCategory = new PmsProductAttributeCategory();
        pmsProductAttributeCategory.setId(id);
        pmsProductAttributeCategory.setName(name);
        return productAttributeCategoryMapper.updateByPrimaryKeySelective(pmsProductAttributeCategory);
    }

    /**
     * 根据id删除商品分类属性
     * */
    @Override
    public int delete(Long id) {
        return productAttributeCategoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id查询商品分类属性
     * */
    @Override
    public PmsProductAttributeCategory getItem(Long id) {
        return productAttributeCategoryMapper.selectByPrimaryKey(id);
    }


    /**
     * 分页查询商品分类属性
     * */
    @Override
    public List<PmsProductAttributeCategory> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        return productAttributeCategoryMapper.selectByExample(new PmsProductAttributeCategoryExample());
    }

    /**
     * 获取所有商品属性分类及其下属性
     * */
    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        return productAttributeCategoryDao.getListWithAttr();
    }
}
