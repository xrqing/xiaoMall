package com.xiao.xiaomall.admin.service.impl;

import com.xiao.xiaomall.admin.service.CmsPrefrenceAreaService;
import com.xiao.xiaomall.entity.CmsPrefrenceArea;
import com.xiao.xiaomall.entity.CmsPrefrenceAreaExample;
import com.xiao.xiaomall.mapper.CmsPrefrenceAreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *商品优选的实现  serviceImpl
 **/
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {

    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;

    /**
     *获取所有的商品优选列表
     * */
    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}
