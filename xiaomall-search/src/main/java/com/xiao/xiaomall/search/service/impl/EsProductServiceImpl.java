package com.xiao.xiaomall.search.service.impl;

import com.xiao.xiaomall.search.dao.EsProductDao;
import com.xiao.xiaomall.search.domain.EsProduct;
import com.xiao.xiaomall.search.domain.EsProductRelatedInfo;
import com.xiao.xiaomall.search.repository.EsProductRepository;
import com.xiao.xiaomall.search.service.EsProductService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.jsf.FacesContextUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 *商品搜索管理 serviceImpl
 **/
@Service
public class EsProductServiceImpl implements EsProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);

    @Autowired
    private EsProductRepository productRepository;

    @Autowired
    private EsProductDao productDao;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 从数据库中导入所有的商品到ES
     * */
    @Override
    public int importAll() {
        List<EsProduct> esProductList = productDao.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()){
            result++;
            iterator.next();
        }
        return result;
    }

    /**
     * 根据关键字搜索名称或者副标题
     */
    @Override
    public Page<EsProduct> search(String keyword, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageSize, pageNum);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword,keyword,keyword,pageable);
    }

    /**
     * 根据id去创建商品
     * */
    @Override
    public EsProduct create(Long id) {
        EsProduct result = null;
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size()>0){
            EsProduct product = esProductList.get(0);
            result = productRepository.save(product);
        }
        return result;
    }

    /**
     *根据id去删除商品
     * */
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    /**
     *批量删除商品
     * */
    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)){
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }

    /**
     * 根据关键字搜索名称或者副标题复合查询
     */
    @Override
    public Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageSize, Integer pageNum, Integer sort) {
        Pageable pageable = PageRequest.of(pageSize, pageNum);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
        //过滤
        if (brandId!=null || productCategoryId!=null){
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (brandId!=null){
                boolQueryBuilder.must(QueryBuilders.termQuery("brandId",brandId));
            }
            if (productCategoryId!=null){
                boolQueryBuilder.must(QueryBuilders.termQuery("productCategoryId",productCategoryId));
            }
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }
        //搜索(权重查询)
        if (StringUtils.isEmpty(keyword)){
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        }else{
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name",keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("subTitle",keyword),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords",keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        //排序
        if(sort==1){
            //按新品从新到旧
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        }else if (sort==2){
            //按销量从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("sale").order(SortOrder.DESC));
        }else if (sort==3){
            //按价格从低到高
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        }else if (sort==4){
            //按价格从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        }else {
            //相关度
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        }
        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
        return productRepository.search(searchQuery);
    }
    /**
     * 根据商品的id推荐相关商品
     * */
    @Override
    public Page<EsProduct> recommend(Long id, Integer pageSize, Integer pageNum) {
        //分页
        Pageable pageable = PageRequest.of(pageSize, pageNum);
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if(esProductList.size()>0){
            EsProduct esProduct = esProductList.get(0);
            String keyword = esProduct.getName();
            Long brandId = esProduct.getBrandId();
            Long productCategoryId = esProduct.getProductCategoryId();
            //根据商品标题、品牌、分类进行搜索
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name",keyword),
                    ScoreFunctionBuilders.weightFactorFunction(8)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("subTitle",keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords",keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("brandId",brandId),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("productCategoryId",productCategoryId),
                    ScoreFunctionBuilders.weightFactorFunction(6)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
            builder.withQuery(functionScoreQueryBuilder);
            builder.withPageable(pageable);
            NativeSearchQuery searchQuery = builder.build();
            LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
            return productRepository.search(searchQuery);
        }
        return new PageImpl<>(null);
    }

    /**
     *获取搜索词相关的品牌，分类，属性
     * */
    @Override
    public EsProductRelatedInfo searchRelatedInfo(String keyword) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //搜索条件
        if(StringUtils.isEmpty(keyword)){
            builder.withQuery(QueryBuilders.matchAllQuery());
        }else{
            builder.withQuery(QueryBuilders.multiMatchQuery(keyword,"name","subTitle","keywords"));
        }
        //聚合搜索品牌名称
        builder.addAggregation(AggregationBuilders.terms("brandNames").field("brandName"));
        //集合搜索分类名称
        builder.addAggregation(AggregationBuilders.terms("productCategoryNames").field("productCategoryName"));
        //聚合搜索商品属性，去除type=1的属性
        AbstractAggregationBuilder aggregationBuilder = AggregationBuilders.nested("allAttrValues","attrValueList")
                .subAggregation(AggregationBuilders.filter("productAttrs",QueryBuilders.termQuery("attrValueList.type",1))
                        .subAggregation(AggregationBuilders.terms("attrIds")
                                .field("attrValueList.productAttributeId")
                                .subAggregation(AggregationBuilders.terms("attrValues")
                                        .field("attrValueList.value"))
                                .subAggregation(AggregationBuilders.terms("attrNames")
                                        .field("attrValueList.name"))));
        builder.addAggregation(aggregationBuilder);
        NativeSearchQuery searchQuery = builder.build();
        return elasticsearchTemplate.query(searchQuery, response -> {
            LOGGER.info("DSL:{}",searchQuery.getQuery().toString());
            return convertProductRelatedInfo(response);
        });
    }

    /**
     * 将返回结果转换为对象
     */
    private EsProductRelatedInfo convertProductRelatedInfo(SearchResponse response) {
        EsProductRelatedInfo productRelatedInfo = new EsProductRelatedInfo();
        Map<String, Aggregation> aggregationMap = response.getAggregations().getAsMap();
        //设置品牌
        Aggregation brandNames = aggregationMap.get("brandNames");
        List<String> brandNameList = new ArrayList<>();
        for(int i = 0; i<((Terms) brandNames).getBuckets().size(); i++){
            brandNameList.add(((Terms) brandNames).getBuckets().get(i).getKeyAsString());
        }
        productRelatedInfo.setBrandNames(brandNameList);
        //设置分类
        Aggregation productCategoryNames = aggregationMap.get("productCategoryNames");
        List<String> productCategoryNameList = new ArrayList<>();
        for(int i=0;i<((Terms) productCategoryNames).getBuckets().size();i++){
            productCategoryNameList.add(((Terms) productCategoryNames).getBuckets().get(i).getKeyAsString());
        }
        productRelatedInfo.setProductCategoryNames(productCategoryNameList);
        //设置参数
        Aggregation productAttrs = aggregationMap.get("allAttrValues");
        return productRelatedInfo;
    }
}
