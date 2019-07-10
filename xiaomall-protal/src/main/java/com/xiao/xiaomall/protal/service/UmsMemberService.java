package com.xiao.xiaomall.protal.service;

import com.xiao.xiaomall.entity.UmsMember;

/**
 *会员管理  service
 **/
public interface UmsMemberService {

    /**
     * 根据用户名获取会员
     * */
    UmsMember getByUsername(String username);
}
