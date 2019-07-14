package com.xiao.xiaomall.protal.service;

import com.xiao.xiaomall.protal.domain.MemberReadHistory;

import java.util.List;

/**
 *会员历史记录 service
 **/
public interface MemberReadHistoryService {

    /**
     *增加历史记录
     * */
    int add(MemberReadHistory readHistory);

    /**
     *删除
     * */
    int delete(List<String> ids);

    /**
     * 查询会员历史记录
     * */
    List<MemberReadHistory> list(Long memberId);
}
