package com.xiao.xiaomall.mapper;

import com.xiao.xiaomall.entity.PmsMemberPrice;
import com.xiao.xiaomall.entity.PmsMemberPrice;
import com.xiao.xiaomall.entity.PmsMemberPriceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsMemberPriceMapper {
    int countByExample(PmsMemberPriceExample example);

    int deleteByExample(PmsMemberPriceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsMemberPrice record);

    int insertSelective(PmsMemberPrice record);

    List<PmsMemberPrice> selectByExample(PmsMemberPriceExample example);

    PmsMemberPrice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsMemberPrice record, @Param("example") PmsMemberPriceExample example);

    int updateByExample(@Param("record") PmsMemberPrice record, @Param("example") PmsMemberPriceExample example);

    int updateByPrimaryKeySelective(PmsMemberPrice record);

    int updateByPrimaryKey(PmsMemberPrice record);
}