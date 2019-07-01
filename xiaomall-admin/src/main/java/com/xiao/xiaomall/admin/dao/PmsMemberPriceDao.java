package com.xiao.xiaomall.admin.dao;

import com.xiao.xiaomall.entity.PmsMemberPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *自定义会员价格Dao
 **/
public interface PmsMemberPriceDao {
    int insertList(@Param("list")List<PmsMemberPrice> memberPriceList);
}
