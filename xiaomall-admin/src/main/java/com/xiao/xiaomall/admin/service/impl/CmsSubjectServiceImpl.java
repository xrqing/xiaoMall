package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.service.CmsSubjectService;
import com.xiao.xiaomall.entity.CmsSubject;
import com.xiao.xiaomall.entity.CmsSubjectExample;
import com.xiao.xiaomall.mapper.CmsSubjectMapper;
import net.sf.jsqlparser.statement.execute.Execute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 *商品专题管理 serviceImpl
 **/
@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {

    @Autowired
    private CmsSubjectMapper subjectMapper;

    /**
     *查询所有商品专题
     * */
    @Override
    public List<CmsSubject> listAll() {
        return subjectMapper.selectByExample(new CmsSubjectExample());
    }

    /**
     * 分页查询商品专题
     * */
    @Override
    public List<CmsSubject> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        CmsSubjectExample example = new CmsSubjectExample();
        CmsSubjectExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)){
            criteria.andTitleLike("%"+keyword+"%");
        }
        return subjectMapper.selectByExample(example);
    }
}
