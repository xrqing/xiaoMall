package com.xiao.xiaomall.protal.service.impl;

import com.xiao.xiaomall.protal.domain.MemberBrandAttention;
import com.xiao.xiaomall.protal.repository.MemberBrandAttentionRepository;
import com.xiao.xiaomall.protal.service.MemberAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *会员关注serviceImpl
 **/
@Service
public class MemberAttentionServiceImpl implements MemberAttentionService {

    @Autowired
    private MemberBrandAttentionRepository memberBrandAttentionRepository;

    /**
     * 添加会员关注
     * */
    @Override
    public int add(MemberBrandAttention brandAttention) {
        int count = 0;
        MemberBrandAttention repository = memberBrandAttentionRepository.findByMemberIdAndBrandId(brandAttention.getMemberId(), brandAttention.getBrandId());
        if (repository==null){
            memberBrandAttentionRepository.save(brandAttention);
            count=1;
        }
        return count;
    }

    /**
     * 取消关注
     * */
    @Override
    public int delete(Long memberId, Long brandId) {
        return memberBrandAttentionRepository.deleteByMemberIdAndBrandId(memberId,brandId);
    }

    /**
     * 获取用户关注列表
     */
    @Override
    public List<MemberBrandAttention> list(Long memberId) {
        return memberBrandAttentionRepository.findByMemberId(memberId);
    }
}
