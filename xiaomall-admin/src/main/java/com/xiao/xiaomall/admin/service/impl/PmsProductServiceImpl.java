package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.dao.*;
import com.xiao.xiaomall.admin.dto.PmsProductParam;
import com.xiao.xiaomall.admin.dto.PmsProductQueryParam;
import com.xiao.xiaomall.admin.dto.PmsProductResult;
import com.xiao.xiaomall.admin.service.PmsProductService;
import com.xiao.xiaomall.entity.*;
import com.xiao.xiaomall.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *商品管理的实现
 **/
@Service
public class PmsProductServiceImpl implements PmsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);

    @Autowired
    private PmsProductMapper productMapper;

    @Autowired
    private PmsMemberPriceDao memberPriceDao;

    @Autowired
    private PmsProductLadderDao productLadderDao;

    @Autowired
    private PmsProductFullReductionDao productFullReductionDao;

    @Autowired
    private PmsSkuStockDao skuStockDao;

    @Autowired
    private PmsProductAttributeValueDao productAttributeValueDao;

    @Autowired
    private CmsSubjectProductRelationDao subjectProductRelationDao;

    @Autowired
    private CmsPrefrenceAreaProductRelationDao prefrenceAreaProductRelationDao;

    @Autowired
    private PmsProductDao productDao;

    @Autowired
    private PmsMemberPriceMapper memberPriceMapper;

    @Autowired
    private PmsProductLadderMapper productLadderMapper;

    @Autowired
    private PmsProductFullReductionMapper productFullReductionMapper;

    @Autowired
    private PmsSkuStockMapper skuStockMapper;

    @Autowired
    private PmsProductAttributeValueMapper productAttributeValueMapper;

    @Autowired
    private CmsSubjectProductRelationMapper subjectProductRelationMapper;

    @Autowired
    private CmsPrefrenceAreaProductRelationMapper prefrenceAreaProductRelationMapper;

    @Autowired
    private PmsProductVertifyRecordDao productVertifyRecordDao;

    /**
     *增加商品
     * */
    @Override
    public int create(PmsProductParam productParam) {
        int count;
        //创建商品
        PmsProduct product = productParam;
        product.setId(null);
        productMapper.insertSelective(product);
        //根据促销类型设置价格：阶梯价格,满减价格
        Long productId = product.getId();
        //会员价格
        relateAndInsertList(memberPriceDao,productParam.getMemberPriceList(),productId);
        //阶梯价格
        relateAndInsertList(productLadderDao,productParam.getProductLadderList(),productId);
        //满减价格
        relateAndInsertList(productFullReductionDao,productParam.getProductFullReductionList(),productId);
        //处理sku的编码
        handleSkuStockCode(productParam.getSkuStockList(),productId);
        //添加sku库存信息
        relateAndInsertList(skuStockDao,productParam.getSkuStockList(),productId);
        //添加商品参数,添加自定义商品规格
        relateAndInsertList(productAttributeValueDao,productParam.getProductAttributeValueList(),productId);
        //关联专题
        relateAndInsertList(subjectProductRelationDao,productParam.getSubjectProductRelationList(),productId);
        //关联优选
        relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), productId);
        count = 1;
        return count;
    }

    /**
     *根据商品的编号去查询商品信息
     **/
    @Override
    public PmsProductResult getUpdateInfo(Long id) {
        return productDao.getUpdateInfo(id);
    }

    /**
     * 根据id去更新商品
     * */
    @Override
    public int update(Long id, PmsProductParam productParam) {
        int count;
        PmsProduct product = productParam;
        product.setId(id);
        productMapper.updateByPrimaryKeySelective(product);
        //会员价格（先根据id去查找，找到后删除，在添加）
        PmsMemberPriceExample memberPriceExample = new PmsMemberPriceExample();
        memberPriceExample.createCriteria().andProductIdEqualTo(id);
        memberPriceMapper.deleteByExample(memberPriceExample);
        relateAndInsertList(memberPriceDao,productParam.getMemberPriceList(),id);
        //阶梯价格
        PmsProductLadderExample productLadderExample = new PmsProductLadderExample();
        productLadderExample.createCriteria().andProductIdEqualTo(id);
        productLadderMapper.deleteByExample(productLadderExample);
        relateAndInsertList(productLadderDao,productParam.getProductLadderList(),id);
        //满减价格
        PmsProductFullReductionExample productFullReductionExample = new PmsProductFullReductionExample();
        productFullReductionExample.createCriteria().andProductIdEqualTo(id);
        productFullReductionMapper.deleteByExample(productFullReductionExample);
        relateAndInsertList(productFullReductionDao,productParam.getProductFullReductionList(),id);
        //修改sku库存信息
        PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
        skuStockExample.createCriteria().andProductIdEqualTo(id);
        skuStockMapper.deleteByExample(skuStockExample);
        handleSkuStockCode(productParam.getSkuStockList(),id);
        relateAndInsertList(skuStockDao,productParam.getSkuStockList(),id);
        //添加商品参数,添加自定义商品规格
        PmsProductAttributeValueExample productAttributeValueExample = new PmsProductAttributeValueExample();
        productAttributeValueExample.createCriteria().andProductIdEqualTo(id);
        productAttributeValueMapper.deleteByExample(productAttributeValueExample);
        relateAndInsertList(productAttributeValueDao,productParam.getProductAttributeValueList(),id);
        //关联专题
        CmsSubjectProductRelationExample subjectProductRelationExample = new CmsSubjectProductRelationExample();
        subjectProductRelationExample.createCriteria().andProductIdEqualTo(id);
        subjectProductRelationMapper.deleteByExample(subjectProductRelationExample);
        relateAndInsertList(subjectProductRelationDao,productParam.getSubjectProductRelationList(),id);
        //关联优选
        CmsPrefrenceAreaProductRelationExample prefrenceAreaProductRelationExample = new CmsPrefrenceAreaProductRelationExample();
        prefrenceAreaProductRelationExample.createCriteria().andProductIdEqualTo(id);
        prefrenceAreaProductRelationMapper.deleteByExample(prefrenceAreaProductRelationExample);
        relateAndInsertList(prefrenceAreaProductRelationDao,productParam.getPrefrenceAreaProductRelationList(),id);
        count=1;
        return count;
    }

    /**
     *查询商品分页
     * */
    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        PmsProductExample example = new PmsProductExample();
        PmsProductExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if (productQueryParam.getPublishStatus()!=null){
            criteria.andPublishStatusEqualTo(productQueryParam.getPublishStatus());
        }
        if (productQueryParam.getVerifyStatus()!=null){
            criteria.andVerifyStatusEqualTo(productQueryParam.getVerifyStatus());
        }
        if (!StringUtils.isEmpty(productQueryParam.getKeyword())){
            criteria.andNameLike(""+productQueryParam.getKeyword()+"%");
        }
        if (!StringUtils.isEmpty(productQueryParam.getProductSn())){
            criteria.andProductSnEqualTo(productQueryParam.getProductSn());
        }
        if (productQueryParam.getProductCategoryId()!=null){
            criteria.andProductCategoryIdEqualTo(productQueryParam.getProductCategoryId());
        }
        if (productQueryParam.getBrandId()!=null){
            criteria.andBrandIdEqualTo(productQueryParam.getBrandId());
        }
        return productMapper.selectByExample(example);
    }

    /**
     *批量修改审核状态
     * */
    @Override
    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        PmsProduct product = new PmsProduct();
        product.setVerifyStatus(verifyStatus);
        PmsProductExample productExample = new PmsProductExample();
        productExample.createCriteria().andIdIn(ids);
        List<PmsProductVertifyRecord> list = new ArrayList<>();
        int count = productMapper.updateByExampleSelective(product, productExample);
        for (Long id : ids) {
            PmsProductVertifyRecord record = new PmsProductVertifyRecord();
            record.setCreateTime(new Date());
            record.setDetail(detail);
            record.setVertifyMan("admin");
            record.setProductId(id);
            record.setStatus(verifyStatus);
            list.add(record);
        }
        productVertifyRecordDao.insertList(list);
        return count;
    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, Long productId) {
        if(CollectionUtils.isEmpty(skuStockList))return;
        for(int i=0;i<skuStockList.size();i++){
            PmsSkuStock skuStock = skuStockList.get(i);
            if(StringUtils.isEmpty(skuStock.getSkuCode())){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i+1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }

    /**
     * 建立和插入关系表操作
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            LOGGER.warn("创建产品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
