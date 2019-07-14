package com.xiao.xiaomall.protal.service.impl;

import com.xiao.xiaomall.protal.domain.MemberProductCollection;
import com.xiao.xiaomall.protal.repository.MemberProductCollectionRepository;
import com.xiao.xiaomall.protal.service.MemberCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *会员的收藏  serviceImpl
 **/
@Service
public class MemberCollectionServiceImpl implements MemberCollectionService {

    @Autowired
    private MemberProductCollectionRepository productCollectionRepository;

    /**
     *增加会员的收藏
     * */
    @Override
    public int add(MemberProductCollection productCollection) {
        int count=0;
        MemberProductCollection collection = productCollectionRepository.findByMemberIdAndProductId(productCollection.getMemberId(), productCollection.getProductId());
        if (collection==null){
            productCollectionRepository.save(productCollection);
            count=1;

        }
        return count;
    }

    /**
     *删除收藏
     * */
    @Override
    public int deleteProduct(Long memberId, Long productId) {
        return productCollectionRepository.deleteByMemberIdAndProductId(memberId, productId);
    }

    /**
     *查询
     * */
    @Override
    public List<MemberProductCollection> list(Long memberId) {
        return productCollectionRepository.findByMemberId(memberId);
    }
}
