package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.dao.OmsOrderReturnApplyDao;
import com.xiao.xiaomall.admin.dto.OmsOrderReturnApplyResult;
import com.xiao.xiaomall.admin.dto.OmsReturnApplyQueryParam;
import com.xiao.xiaomall.admin.dto.OmsUpdateStatusParam;
import com.xiao.xiaomall.admin.service.OmsOrderReturnApplyService;
import com.xiao.xiaomall.entity.OmsOrderReturnApply;
import com.xiao.xiaomall.entity.OmsOrderReturnApplyExample;
import com.xiao.xiaomall.mapper.OmsOrderReturnApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *订单退货处理 serviceImpl
 **/
@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {

    @Autowired
    private OmsOrderReturnApplyMapper orderReturnApplyMapper;

    @Autowired
    private OmsOrderReturnApplyDao orderReturnApplyDao;

    /**
     *查询订单退货
     * */
    @Override
    public List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        return orderReturnApplyDao.getList(queryParam);
    }

    /**
     *批量删除
     * */
    @Override
    public int delete(List<Long> ids) {
        OmsOrderReturnApplyExample example = new OmsOrderReturnApplyExample();
        example.createCriteria().andIdIn(ids);
        return orderReturnApplyMapper.deleteByExample(example);
    }

    /**
     *根据id去查询退货订单
     * */
    @Override
    public OmsOrderReturnApplyResult getItem(Long id) {
        return orderReturnApplyDao.getDetail(id);
    }

    /**
     * 根据id去修改申请状态(确认和拒绝退货)
     * */
    @Override
    public int updateStatus(Long id, OmsUpdateStatusParam param) {
        //根据状态去处理
        Integer status = param.getStatus();
        OmsOrderReturnApply apply = new OmsOrderReturnApply();
        if (status==1){//确认退货
            //处理退货信息
            apply.setId(id);
            apply.setStatus(1);
            apply.setCompanyAddressId(param.getCompanyAddressId());
            apply.setReturnAmount(param.getReturnAmount());
            //记录操作人信息
            apply.setHandleMan(param.getHandleMan());
            apply.setHandleTime(new Date());
            apply.setHandleNote(param.getHandleNote());
        }else if (status==2){//已完成（只需记录操作人信息）
            apply.setId(id);
            apply.setStatus(status);
            apply.setHandleNote(param.getHandleNote());
            apply.setHandleTime(new Date());
            apply.setHandleMan(param.getHandleMan());
        }else if (status==3){//已拒绝（只需记录操作人信息）
            apply.setId(id);
            apply.setStatus(status);
            apply.setHandleMan(param.getHandleMan());
            apply.setHandleTime(new Date());
            apply.setHandleNote(param.getHandleNote());
        }else {//待处理
            return 0;
        }
        return orderReturnApplyMapper.updateByPrimaryKeySelective(apply);
    }
}
