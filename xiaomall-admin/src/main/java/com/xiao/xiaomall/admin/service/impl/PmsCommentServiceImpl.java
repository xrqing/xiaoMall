package com.xiao.xiaomall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiao.xiaomall.admin.service.PmsCommentService;
import com.xiao.xiaomall.entity.PmsComment;
import com.xiao.xiaomall.entity.PmsCommentExample;
import com.xiao.xiaomall.mapper.PmsCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 *评价管理  serviceImpl
 **/
@Service
public class PmsCommentServiceImpl implements PmsCommentService {

    @Autowired
    private PmsCommentMapper commentMapper;

    /**
     * 增加评价
     * */
    @Override
    public int add(PmsComment comment) {
        comment.setCreateTime(new Date());
        int count = commentMapper.insert(comment);
        return count;
    }

    /**
     *根据id删除评价
     * */
    @Override
    public int deleteOne(Long id) {
        int count = commentMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     *根据id批量删除评价
     * */
    @Override
    public int deleteBatch(List<Long> ids) {
        PmsCommentExample example = new PmsCommentExample();
        example.createCriteria().andIdIn(ids);
        int count = commentMapper.deleteByExample(example);
        return count;
    }

    /**
     *根据id修改显示状态
     * */
    @Override
    public int updateShowStatusOne(Long id, Integer showStatus) {
        PmsComment comment = new PmsComment();
        comment.setId(id);
        comment.setShowStatus(showStatus);
        int count = commentMapper.updateByPrimaryKeySelective(comment);
        return count;
    }

    /**
     *根据id批量修改显示状态
     * */
    @Override
    public int updateShowStatusBatch(List<Long> ids, Integer showStatus) {
        PmsComment comment = new PmsComment();
        comment.setShowStatus(showStatus);
        PmsCommentExample example = new PmsCommentExample();
        example.createCriteria().andIdIn(ids);
        int count = commentMapper.updateByExampleSelective(comment, example);
        return count;
    }

    /**
     *根据id查看评价详情
     * */
    @Override
    public PmsComment getItem(Long id) {
        PmsComment pmsComment = commentMapper.selectByPrimaryKey(id);
        return pmsComment;
    }


    /**
     *根据商品名，商品分类，昵称获取评价分页
     * */
    @Override
    public List<PmsComment> list(String productName, String memberNickName, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        PmsCommentExample example = new PmsCommentExample();
        PmsCommentExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(productName)){
            criteria.andProductNameEqualTo("%"+productName+"%");
        }
        if (!StringUtils.isEmpty(memberNickName)){
            criteria.andMemberIconEqualTo("%"+memberNickName+"%");
        }
        List<PmsComment> list = commentMapper.selectByExample(example);
        return list;
    }
}
