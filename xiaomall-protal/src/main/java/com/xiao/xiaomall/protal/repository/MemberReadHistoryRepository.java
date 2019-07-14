package com.xiao.xiaomall.protal.repository;

import com.xiao.xiaomall.protal.domain.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 *会员浏览历史记录 Repository
 **/
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {
    /**
     * 查询历史记录
     * */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
}
