package com.xiao.xiaomall.admin.dao;

import com.xiao.xiaomall.admin.dto.SmsCouponParam;
import org.apache.ibatis.annotations.Param;

/**
 *优惠卷管理自定义DAO
 **/
public interface SmsCouponDao {
    SmsCouponParam getItem(@Param("id") Long id);
}
