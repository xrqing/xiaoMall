package com.xiao.xiaomall.protal.repository;

import com.xiao.xiaomall.protal.domain.MemberBrandAttention;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 *会员关注 repository
 **/
public interface MemberBrandAttentionRepository extends MongoRepository<MemberBrandAttention,String> {

    /**
     *添加会员的关注
     * */
    MemberBrandAttention findByMemberIdAndBrandId(Long memberId,Long brandId);

    /**
     * 取消关注
     * */
    int deleteByMemberIdAndBrandId(Long memberId,Long brandId);

    /**
     * 获取用户关注列表
     */
    List<MemberBrandAttention> findByMemberId(Long memberId);
}
