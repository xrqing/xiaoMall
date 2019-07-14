package com.xiao.xiaomall.protal.service;

import com.xiao.xiaomall.protal.domain.MemberBrandAttention;

import java.util.List;

/**
 *会员关注  service
 **/
public interface MemberAttentionService {

    /**
     *添加关注
     * */
    int add(MemberBrandAttention brandAttention);

    /**
     * 取消关注
     * */
    int delete(Long memberId,Long brandId);

    /**
     * 获取用户关注列表
     */
    List<MemberBrandAttention> list(Long memberId);
}
