package com.xiao.xiaomall.protal.service.impl;

import com.xiao.xiaomall.entity.UmsMember;
import com.xiao.xiaomall.entity.UmsMemberExample;
import com.xiao.xiaomall.entity.UmsMemberLevelExample;
import com.xiao.xiaomall.mapper.UmsMemberMapper;
import com.xiao.xiaomall.protal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *会员管理实现  serviceImpl
 **/
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private UmsMemberMapper memberMapper;

    /**
     *根据用户名获取会员
     * */
    @Override
    public UmsMember getByUsername(String username) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)){
            return memberList.get(0);
        }
        return null;
    }
}
