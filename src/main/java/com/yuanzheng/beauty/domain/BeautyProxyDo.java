package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * beauty_proxy
 */
public class BeautyProxyDo implements Serializable {
    private Integer id;

    /**
     * 代理名 比如一级代理
     */
    private String name;

    /**
     * 人数区间开始
     */
    private Integer startnum;

    /**
     * 人数区间结束
     */
    private Integer emdnum;

    /**
     * 分红 %
     */
    private Integer dividends;

    /**
     * 优惠折扣 %
     */
    private Integer productOffers;

    /**
     * 受保人数
     */
    private Integer peopleInsured;

    /**
     * 医疗保险报销
     */
    private Integer mir;

    private Date createdTime;

    private Date updatedTime;

    /**
     * 展示顺序
     */
    private Integer sore;

    /**
     * 启用状态 1 启用 0 禁用
     */
    private Integer status;

    /**
     * 是否为默认代理层级
     */
    private Integer def;

    private static final long serialVersionUID = 1L;
}