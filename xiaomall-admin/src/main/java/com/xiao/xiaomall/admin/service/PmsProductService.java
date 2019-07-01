package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.admin.dto.PmsProductParam;
import com.xiao.xiaomall.admin.dto.PmsProductResult;
import org.springframework.transaction.annotation.Transactional;

/**
 *商品管理 service
 **/
public interface PmsProductService {

    /**
     *增加商品
     * */
    @Transactional
    int create(PmsProductParam pmsProductParam);

    /**
     *根据商品的编号去查询更新的信息
     **/
    PmsProductResult getUpdateInfo(Long id);
}
