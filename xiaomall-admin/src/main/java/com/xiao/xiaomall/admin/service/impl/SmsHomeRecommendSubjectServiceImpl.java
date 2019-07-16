package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.service.SmsHomeRecommendProductService;
import com.xiao.xiaomall.admin.service.SmsHomeRecommendSubjectService;
import com.xiao.xiaomall.entity.SmsHomeRecommendProduct;
import com.xiao.xiaomall.entity.SmsHomeRecommendSubject;
import com.xiao.xiaomall.entity.SmsHomeRecommendSubjectExample;
import com.xiao.xiaomall.mapper.SmsHomeRecommendProductMapper;
import com.xiao.xiaomall.mapper.SmsHomeRecommendSubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 *专题推荐  serviceImpl
 **/
@Service
public class SmsHomeRecommendSubjectServiceImpl implements SmsHomeRecommendSubjectService {

    @Autowired
    private SmsHomeRecommendSubjectMapper homeRecommendSubjectMapper;

    /**
     * 增加专题推荐
     * */
    @Override
    public int add(List<SmsHomeRecommendSubject> homeRecommendSubjectList) {
        for (SmsHomeRecommendSubject smsHomeRecommendSubject : homeRecommendSubjectList) {
            smsHomeRecommendSubject.setRecommendStatus(1);
            smsHomeRecommendSubject.setSort(0);
            homeRecommendSubjectMapper.insert(smsHomeRecommendSubject);
        }
        int size = homeRecommendSubjectList.size();
        return size;
    }

    /**
     *根据id删除专题推荐
     * */
    @Override
    public int deleteOne(Long id) {
        int count = homeRecommendSubjectMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 根据id批量删除专题推荐
     * */
    @Override
    public int deleteBatch(List<Long> ids) {
        SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        example.createCriteria().andIdIn(ids);
        int count = homeRecommendSubjectMapper.deleteByExample(example);
        return count;
    }

    /**
     *根据id设置专题推荐
     * */
    @Override
    public int updateRecommendStatusOne(Long id, Integer recommendStatus) {
        SmsHomeRecommendSubject homeRecommendSubject = new SmsHomeRecommendSubject();
        homeRecommendSubject.setId(id);
        homeRecommendSubject.setRecommendStatus(recommendStatus);
        int count = homeRecommendSubjectMapper.updateByPrimaryKeySelective(homeRecommendSubject);
        return count;
    }

    /**
     *根据id批量设置专题推荐
     *  * */
    @Override
    public int updateRecommendStatusBatch(List<Long> ids, Integer recommendStatus) {
        SmsHomeRecommendSubject homeRecommendSubject = new SmsHomeRecommendSubject();
        homeRecommendSubject.setRecommendStatus(recommendStatus);
        SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        example.createCriteria().andIdIn(ids);
        int count = homeRecommendSubjectMapper.updateByExampleSelective(homeRecommendSubject, example);
        return count;
    }

    /**
     根据id设置排序
     * */
    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendSubject homeRecommendSubject = new SmsHomeRecommendSubject();
        homeRecommendSubject.setId(id);
        homeRecommendSubject.setSort(sort);
        int count = homeRecommendSubjectMapper.updateByPrimaryKeySelective(homeRecommendSubject);
        return count;
    }

    /**
     *根据名称和状态获取分页
     * */
    @Override
    public List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        SmsHomeRecommendSubject smsHomeRecommendSubject = new SmsHomeRecommendSubject();
        smsHomeRecommendSubject.setRecommendStatus(recommendStatus);
        SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        SmsHomeRecommendSubjectExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(subjectName)){
            criteria.andSubjectNameEqualTo("%"+subjectName+"%");
        }
        if (!StringUtils.isEmpty(recommendStatus)) {
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        List<SmsHomeRecommendSubject> smsHomeRecommendSubjects = homeRecommendSubjectMapper.selectByExample(example);
        return smsHomeRecommendSubjects;
    }
}
