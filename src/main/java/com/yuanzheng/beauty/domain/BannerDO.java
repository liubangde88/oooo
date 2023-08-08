package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * @author wang
 * @email 1992lcg@163.com
 * @date 2021-06-08 15:27:15
 */
public class BannerDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //广告类型
    private String bannerType;
    //广告稿位
    private String bannerSite;
    //链接
    private String bannerUrl;
    //图片
    private String bannerCover;
    //有效期
    private Date bannerBegin;
    //有效期
    private Date bannerEnd;
    //
    private Date createTime;
    //
    private Date updateTime;

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：广告类型
     */
    public String getBannerType() {
        return bannerType;
    }

    /**
     * 设置：广告类型
     */
    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    /**
     * 获取：广告稿位
     */
    public String getBannerSite() {
        return bannerSite;
    }

    /**
     * 设置：广告稿位
     */
    public void setBannerSite(String bannerSite) {
        this.bannerSite = bannerSite;
    }

    /**
     * 获取：链接
     */
    public String getBannerUrl() {
        return bannerUrl;
    }

    /**
     * 设置：链接
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    /**
     * 获取：图片
     */
    public String getBannerCover() {
        return bannerCover;
    }

    /**
     * 设置：图片
     */
    public void setBannerCover(String bannerCover) {
        this.bannerCover = bannerCover;
    }

    /**
     * 获取：有效期
     */
    public Date getBannerBegin() {
        return bannerBegin;
    }

    /**
     * 设置：有效期
     */
    public void setBannerBegin(Date bannerBegin) {
        this.bannerBegin = bannerBegin;
    }

    /**
     * 获取：有效期
     */
    public Date getBannerEnd() {
        return bannerEnd;
    }

    /**
     * 设置：有效期
     */
    public void setBannerEnd(Date bannerEnd) {
        this.bannerEnd = bannerEnd;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
