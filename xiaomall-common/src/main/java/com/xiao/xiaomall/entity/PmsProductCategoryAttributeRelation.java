package com.xiao.xiaomall.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 商品属性
 * */
@Data
@ToString
public class PmsProductCategoryAttributeRelation implements Serializable {
    private Long id;

    private Long productCategoryId;

    private Long productAttributeId;

    private static final long serialVersionUID = 1L;

}