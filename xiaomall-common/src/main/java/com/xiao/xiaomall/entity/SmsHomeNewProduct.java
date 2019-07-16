package com.xiao.xiaomall.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *新品推荐
 * */
@Data
@ToString
public class SmsHomeNewProduct implements Serializable {
    private Long id;

    private Long productId;

    private String productName;

    private Integer recommendStatus;

    private Integer sort;

    private static final long serialVersionUID = 1L;
}