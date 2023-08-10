package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * beauty_proxy
 */
public class BeautyProxyDo implements Serializable {

    private static final long serialVersionUID = 1L;


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
     * 医疗保险报销比例 %
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
     * 是否为新注册用户默认代理层级
     */
    private Integer def;

    private String img;
    public BeautyProxyDo(Integer id, String name, Integer startnum, Integer emdnum, Integer dividends, Integer productOffers, Integer peopleInsured, Integer mir, Date createdTime, Date updatedTime, Integer sore, Integer status, Integer def, String img) {
        this.id = id;
        this.name = name;
        this.startnum = startnum;
        this.emdnum = emdnum;
        this.dividends = dividends;
        this.productOffers = productOffers;
        this.peopleInsured = peopleInsured;
        this.mir = mir;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.sore = sore;
        this.status = status;
        this.def = def;
        this.img = img;
    }


    public Integer getEmdnum() {
        return emdnum;
    }

    public void setEmdnum(Integer emdnum) {
        this.emdnum = emdnum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartnum() {
        return startnum;
    }

    public void setStartnum(Integer startnum) {
        this.startnum = startnum;
    }

    public Integer getDividends() {
        return dividends;
    }

    public void setDividends(Integer dividends) {
        this.dividends = dividends;
    }

    public Integer getProductOffers() {
        return productOffers;
    }

    public void setProductOffers(Integer productOffers) {
        this.productOffers = productOffers;
    }

    public Integer getPeopleInsured() {
        return peopleInsured;
    }

    public void setPeopleInsured(Integer peopleInsured) {
        this.peopleInsured = peopleInsured;
    }

    public Integer getMir() {
        return mir;
    }

    public void setMir(Integer mir) {
        this.mir = mir;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getSore() {
        return sore;
    }

    public void setSore(Integer sore) {
        this.sore = sore;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}