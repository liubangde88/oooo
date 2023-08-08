package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.util.Date;

public class DoctorDo implements Serializable {

    private static final long serialVersionUID = -265450572152311731L;


    private Long id;


    private String headImg;


    private String name;


    private String postName;


    private String officeName;


    private String goodAt;


    private String detail;


    private Date createTime;


    private Date updateTime;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getHeadImg() {
        return headImg;
    }


    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }


    public String getPostName() {
        return postName;
    }


    public void setPostName(String postName) {
        this.postName = postName;
    }


    public String getOfficeName() {
        return officeName;
    }


    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }


    public String getGoodAt() {
        return goodAt;
    }


    public void setGoodAt(String goodAt) {
        this.goodAt = goodAt;
    }


    public String getDetail() {
        return detail;
    }


    public void setDetail(String detail) {
        this.detail = detail;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

}
