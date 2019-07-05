package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.entity.CmsSubject;

import java.util.List;

/**
 *商品专题管理 service
 **/
public interface CmsSubjectService {

    /**
     *查询所有的商品专题
     * */
    List<CmsSubject> listAll();

    /**
     * 分页查询商品专题
     * */
    List<CmsSubject> list(String keyword,Integer pageSize,Integer pageNum);
}
