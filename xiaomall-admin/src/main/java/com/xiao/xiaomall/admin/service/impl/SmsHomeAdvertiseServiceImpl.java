package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.service.SmsHomeAdvertiseService;
import com.xiao.xiaomall.entity.SmsHomeAdvertise;
import com.xiao.xiaomall.entity.SmsHomeAdvertiseExample;
import com.xiao.xiaomall.mapper.SmsHomeAdvertiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 *广告推荐  serviceImpl
 **/
@Service
public class SmsHomeAdvertiseServiceImpl implements SmsHomeAdvertiseService {

    @Autowired
    private SmsHomeAdvertiseMapper homeAdvertiseMapper;

    /**
     *增加广告推荐
     * */
    @Override
    public int add(SmsHomeAdvertise homeAdvertise) {
        homeAdvertise.setClickCount(0);
        homeAdvertise.setOrderCount(0);
        int count = homeAdvertiseMapper.insert(homeAdvertise);
        return count;
    }

    /**
     *根据id删除广告推荐
     * */
    @Override
    public int deleteOne(Long id) {
        int count = homeAdvertiseMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     *根据id批量删除广告推荐
     * */
    @Override
    public int deleteBatch(List<Long> ids) {
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.createCriteria().andIdIn(ids);
        int count = homeAdvertiseMapper.deleteByExample(example);
        return count;
    }

    /**
     *根据id去设置推荐状态
     * */
    @Override
    public int updateStatus(Long id, Integer status) {
        SmsHomeAdvertise smsHomeAdvertise = new SmsHomeAdvertise();
        smsHomeAdvertise.setId(id);
        smsHomeAdvertise.setStatus(status);
        int count = homeAdvertiseMapper.updateByPrimaryKeySelective(smsHomeAdvertise);
        return count;
    }

    /**
     *根据id去修改广告推荐
     * */
    @Override
    public int update(Long id, SmsHomeAdvertise homeAdvertise) {
        homeAdvertise.setId(id);
        int count = homeAdvertiseMapper.updateByPrimaryKeySelective(homeAdvertise);
        return count;
    }

    /**
     *根据名称，位置，结束时间获取分页
     * */
    @Override
    public List<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        SmsHomeAdvertiseExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)){
            criteria.andNameEqualTo("%"+name+"%");
        }
        if (type!=null){
            criteria.andTypeEqualTo(type);
        }
        if (!StringUtils.isEmpty(endTime)){
            String startStr = endTime+"00:00:00";
            String endStr = endTime+"23:59:59";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = null;
            try {
                start=format.parse(startStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date end = null;
            try {
                end=format.parse(endStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (start!=null&&end!=null){
                criteria.andEndTimeBetween(start,end);
            }
        }
        example.setOrderByClause("sort desc");
        List<SmsHomeAdvertise> smsHomeAdvertises = homeAdvertiseMapper.selectByExample(example);
        return smsHomeAdvertises;
    }
}
