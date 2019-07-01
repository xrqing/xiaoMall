package com.xiao.xiaomall.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 商品专题
 * */
@Data
@ToString
public class CmsSubjectProductRelation implements Serializable {
    private Long id;

    private Long subjectId;

    private Long productId;

    private static final long serialVersionUID = 1L;

}