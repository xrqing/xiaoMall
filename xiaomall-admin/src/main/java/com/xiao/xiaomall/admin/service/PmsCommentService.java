package com.xiao.xiaomall.admin.service;

import com.xiao.xiaomall.entity.PmsComment;

import java.util.List;

/**
 * 评价 管理  service
 **/
public interface PmsCommentService {

    /**
     *增加评价
     * */
    int add(PmsComment comment);

    /**
     *根据id删除评价
     * */
    int deleteOne(Long id);

    /**
     *根据id批量删除评价
     * */
    int deleteBatch(List<Long> ids);

    /**
     *根据id修改显示状态
     * */
    int updateShowStatusOne(Long id,Integer showStatus);

    /**
     *根据id批量修改显示状态
     * */
    int updateShowStatusBatch(List<Long> ids,Integer showStatus);

    /**
     *根据id查看评价详情
     * */
    PmsComment getItem(Long id);

    /**
     *根据商品名，商品分类，昵称获取评价分页
     * */
    List<PmsComment> list(String productName, String memberNickName, Integer pageSize,Integer pageNum);
}
