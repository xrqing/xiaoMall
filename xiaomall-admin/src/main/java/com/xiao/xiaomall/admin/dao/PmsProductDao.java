package com.xiao.xiaomall.admin.dao;

import com.xiao.xiaomall.admin.dto.PmsProductResult;

/**
 *商品管理自定义dao
 **/
public interface PmsProductDao {
    /**
     * 根据商品的编号去获取编辑信息
     * */
    PmsProductResult getUpdateInfo(Long id);
}
