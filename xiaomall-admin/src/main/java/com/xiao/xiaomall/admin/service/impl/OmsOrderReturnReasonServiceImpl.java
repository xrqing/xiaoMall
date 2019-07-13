package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.service.OmsOrderReturnReasonService;
import com.xiao.xiaomall.entity.OmsOrderReturnReason;
import com.xiao.xiaomall.entity.OmsOrderReturnReasonExample;
import com.xiao.xiaomall.mapper.OmsOrderReturnReasonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *订单退货原因  serviceImpl
 **/
@Service
public class OmsOrderReturnReasonServiceImpl implements OmsOrderReturnReasonService {

    @Autowired
    private OmsOrderReturnReasonMapper orderReturnReasonMapper;

    /**
     * 添加退货原因
     * */
    @Override
    public int create(OmsOrderReturnReason orderReturnReason) {
        orderReturnReason.setCreateTime(new Date());
        return orderReturnReasonMapper.insert(orderReturnReason);
    }

    /**
     *分页获取退货原因
     * */
    @Override
    public List<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        example.setOrderByClause("sort desc");
        return orderReturnReasonMapper.selectByExample(example);
    }

    /**
     *根据id删除退货原因
     * */
    @Override
    public int delete(Long id) {
        return orderReturnReasonMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id批量删除退货原因
     * */
    @Override
    public int deleteAll(List<Long> ids) {
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        example.createCriteria().andIdIn(ids);
        return orderReturnReasonMapper.deleteByExample(example);
    }

    /**
     *根据id去修改退货原因
     * */
    @Override
    public int update(Long id, OmsOrderReturnReason orderReturnReason) {
        orderReturnReason.setId(id);
        return orderReturnReasonMapper.updateByPrimaryKey(orderReturnReason);
    }

    /**
     *批量修改退货状态
     * */
    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        if (!status.equals(0)&&!status.equals(1)){
            return 0;
        }
        OmsOrderReturnReason orderReturnReason = new OmsOrderReturnReason();
        orderReturnReason.setStatus(status);
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        example.createCriteria().andIdIn(ids);
        example.setOrderByClause("sort desc");
        return orderReturnReasonMapper.updateByExampleSelective(orderReturnReason,example);
    }

    /**
     *根据id去修改退货状态
     * */
    @Override
    public int updateStatusOne(Long id, Integer status) {
        OmsOrderReturnReason orderReturnReason = new OmsOrderReturnReason();
        orderReturnReason.setId(id);
        orderReturnReason.setStatus(status);
        int count = orderReturnReasonMapper.updateByPrimaryKeySelective(orderReturnReason);
        return count;
    }

    /**
     *根据id去获取退货详情
     * */
    @Override
    public OmsOrderReturnReason getItem(Long id) {
        return orderReturnReasonMapper.selectByPrimaryKey(id);
    }
}
