package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.entity.SmsHomeRecommendSubject;
import io.swagger.models.auth.In;

import java.util.List;

/**
 *专题推荐  service
 **/
public interface SmsHomeRecommendSubjectService {

    /**
     * 增加专题推荐
     * */
    int add(List<SmsHomeRecommendSubject> homeRecommendSubjectList);

    /**
     *根据id删除专题推荐
     * */
    int deleteOne(Long id);

    /**
     * 根据id批量删除专题推荐
     * */
    int deleteBatch(List<Long> ids);

    /**
     *根据id设置专题推荐
     * */
    int updateRecommendStatusOne(Long id,Integer recommendStatus);

    /**
     *根据id批量设置专题推荐
     *  * */
    int updateRecommendStatusBatch(List<Long> ids, Integer recommendStatus);

    /**
     根据id设置排序
     * */
    int updateSort(Long id,Integer sort);

    /**
     *根据名称和状态获取分页
     * */
    List<SmsHomeRecommendSubject> list(String subjectName,Integer recommendStatus,Integer pageSize,Integer pageNum);
}
