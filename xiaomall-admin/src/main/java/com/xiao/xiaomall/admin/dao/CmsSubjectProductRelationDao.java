package com.xiao.xiaomall.admin.dao;

import com.xiao.xiaomall.entity.CmsSubjectProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *自定义商品和专题关系操作
 **/
public interface CmsSubjectProductRelationDao {
    int insertList(@Param("list") List<CmsSubjectProductRelation> subjectProductRelationList);
}
