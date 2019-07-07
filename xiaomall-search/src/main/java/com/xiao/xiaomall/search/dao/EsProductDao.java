package com.xiao.xiaomall.search.dao;

import com.xiao.xiaomall.search.domain.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *商品搜索自定义dao
 **/
public interface EsProductDao {

    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
