package com.xiao.xiaomall.admin.dao;

import com.xiao.xiaomall.entity.OmsOrderOperateHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *订单操作记录自定义dao
 **/
public interface OmsOrderOperateHistoryDao {
    int insertList(@Param("list")List<OmsOrderOperateHistory> orderOperateHistoryList);
}
