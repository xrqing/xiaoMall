package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.entity.PmsSkuStock;

import java.util.List;

/**
 *商品库存 service
 **/
public interface PmsSkuStockService {

    /**
     *模糊查询(productId,skuCode)
     * */
    List<PmsSkuStock> getList(Long productId,String skuCode);

    /**
     *批量更新商品库存信息
     * */
    int updateStock(Long productId,List<PmsSkuStock> skuStockList);
}
