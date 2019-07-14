package com.xiao.xiaomall.protal.repository;

import com.xiao.xiaomall.protal.domain.MemberProductCollection;
import org.springframework.data.convert.KotlinClassGeneratingEntityInstantiator;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

/**
 *会员的收藏 Repository
 **/
public interface MemberProductCollectionRepository extends MongoRepository<MemberProductCollection,Long> {

    /**
     * 增加收藏
     * */
    MemberProductCollection findByMemberIdAndProductId(Long memberId,Long productId);

    /**
     * 删除收藏
     * */
    int deleteByMemberIdAndProductId(Long memberId,Long productId);

    /**
     *收藏列表
     * */
    List<MemberProductCollection> findByMemberId(Long memberId);
}
