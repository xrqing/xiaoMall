package com.xiao.xiaomall.search.repository;

import com.xiao.xiaomall.search.domain.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 *商品es的操作类
 **/
public interface EsProductRepository extends ElasticsearchRepository<EsProduct,Long> {

    /**
     * 搜索商品
     * */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}
