package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.admin.dto.PmsBrandParam;
import com.xiao.xiaomall.entity.PmsBrand;
import io.swagger.models.auth.In;

import java.security.acl.LastOwnerException;
import java.util.List;

/**
 * 商品品牌 service
 **/
public interface PmsBrandService {
    /***
     *查询全部商品品牌
     */
    List<PmsBrand> listAllBrand();

    /**
     * 添加商品品牌
     * */
    int createBrand(PmsBrandParam brandParam);

    /**
     *修改商品品牌
     * */
    int updateBrand(Long id,PmsBrandParam brandParam);

    /**
     * 删除商品品牌
     * */
    int delete(Long id);

    /**
     * 批量删除商品品牌
     * */
    int deleteAll(List<Long> ids);

    /**
     *分页获取商品品牌
     * */
    List<PmsBrand> list(String keyword, int pageSize, int pageNum);

    /**
     *查询单个商品品牌
     * */
    PmsBrand findOne(Long id);

    /**
     *批量修改显示状态
     * */
    int updateShowStatus(List<Long> ids,Integer showStatus);

    /**
     *批量修改制造商状态
     * */
    int updateFactoryStatus(List<Long> ids,Integer factoryStatus);

}
