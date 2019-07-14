package com.xiao.xiaomall.protal.service;

import com.xiao.xiaomall.protal.domain.MemberProductCollection;

import java.util.List;

/**
 *会员的收藏
 **/
public interface MemberCollectionService {

    /**
     *增加收藏
     * */
    int add(MemberProductCollection productCollection);

    /**
     *删除收藏
     * */
    int deleteProduct(Long memberId,Long productId);

    /**
     *查询
     * */
    List<MemberProductCollection> list(Long memberId);
}
