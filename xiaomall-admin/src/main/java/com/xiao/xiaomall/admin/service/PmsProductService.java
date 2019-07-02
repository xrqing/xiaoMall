package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.admin.dto.PmsProductParam;
import com.xiao.xiaomall.admin.dto.PmsProductQueryParam;
import com.xiao.xiaomall.admin.dto.PmsProductResult;
import com.xiao.xiaomall.entity.PmsProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 根据id去更新商品
     * */
    int update(Long id,PmsProductParam productParam);

    /**
     *查询商品分页
     * */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam,Integer pageSize,Integer pageNum);

    /**
     *批量修改审核状态
     * */
    @Transactional
    int updateVerifyStatus(List<Long> ids,Integer verifyStatus,String detail);
}
