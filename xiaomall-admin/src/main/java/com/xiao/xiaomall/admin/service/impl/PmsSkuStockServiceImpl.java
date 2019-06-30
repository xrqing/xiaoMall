package com.xiao.xiaomall.admin.service.impl;

import com.xiao.xiaomall.admin.dao.PmsSkuStockDao;
import com.xiao.xiaomall.admin.service.PmsSkuStockService;
import com.xiao.xiaomall.entity.PmsSkuStock;
import com.xiao.xiaomall.entity.PmsSkuStockExample;
import com.xiao.xiaomall.mapper.PmsSkuStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 商品库存的实现
 **/
@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {

    @Autowired
    private PmsSkuStockMapper pmsSkuStockMapper;

    @Autowired
    private PmsSkuStockDao skuStockDao;

    /**
     *模糊查询(productId,skuCode)
     * */
    @Override
    public List<PmsSkuStock> getList(Long productId, String skuCode) {
        PmsSkuStockExample example = new PmsSkuStockExample();
        PmsSkuStockExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        if (!StringUtils.isEmpty(skuCode)){
            criteria.andSkuCodeLike("%"+skuCode+"%");
        }
        return pmsSkuStockMapper.selectByExample(example);
    }

    /**
     *批量更新商品库存信息
     * */
    @Override
    public int updateStock(Long productId, List<PmsSkuStock> skuStockList) {
        return skuStockDao.replaceList(skuStockList);
    }
}
