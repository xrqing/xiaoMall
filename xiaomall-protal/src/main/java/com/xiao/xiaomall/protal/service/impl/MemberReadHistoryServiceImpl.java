package com.xiao.xiaomall.protal.service.impl;

import com.xiao.xiaomall.protal.domain.MemberReadHistory;
import com.xiao.xiaomall.protal.repository.MemberReadHistoryRepository;
import com.xiao.xiaomall.protal.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *会员浏览历史记录
 **/
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    @Autowired
    private MemberReadHistoryRepository readHistoryRepository;

    /**
     *增加会员浏览历史记录
     * */
    @Override
    public int add(MemberReadHistory readHistory) {
        readHistory.setId(null);
        readHistory.setCreateTime(new Date());
        readHistoryRepository.save(readHistory);
        return 1;
    }

    /**
     * 批量删除会员浏览历史记录
     * */
    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> deleteList = new ArrayList<>();
        for (String id : ids) {
            MemberReadHistory history = new MemberReadHistory();
            history.setId(id);
            deleteList.add(history);
        }
        readHistoryRepository.deleteAll(deleteList);
        return ids.size();
    }

    /***
     * 查询会员浏览历史记录
     * */
    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return readHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}
