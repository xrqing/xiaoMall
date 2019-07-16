package com.xiao.xiaomall.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 *推荐专题
 * */
@Data
@ToString
public class SmsHomeRecommendSubject implements Serializable {
    private Long id;

    private Long subjectId;

    private String subjectName;

    private Integer recommendStatus;

    private Integer sort;

    private static final long serialVersionUID = 1L;
}