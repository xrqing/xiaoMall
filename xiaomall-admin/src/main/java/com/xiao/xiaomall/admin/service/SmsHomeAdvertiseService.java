package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.entity.SmsHomeAdvertise;
import java.util.List;

/**
 *广告推荐  service
 **/
public interface SmsHomeAdvertiseService {

    /**
     *增加广告推荐
     * */
    int add(SmsHomeAdvertise homeAdvertise);

    /**
     *根据id删除广告推荐
     * */
    int deleteOne(Long id);

    /**
     *根据id批量删除广告推荐
     * */
    int deleteBatch(List<Long> ids);

    /**
     *根据id设置上下线状态
     * */
    int updateStatus(Long id,Integer status);

    /**
     *根据id修改广告推荐
     * */
    int update(Long id,SmsHomeAdvertise homeAdvertise);

    /**
     *根据广告名称，位置，到期时间获取分页
     * */
    List<SmsHomeAdvertise> list(String name, Integer type, String endTime,Integer pageSize,Integer pageNum);
}
