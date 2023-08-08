package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.util.Date;

public class NoticeDo implements Serializable {

    private static final long serialVersionUID = -4211767593768183854L;

    private Long id;


    private String title;


    private String content;


    private int isUp;


    private Date createTime;


    private Date updateTime;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public int getIsUp() {
        return isUp;
    }


    public void setIsUp(int isUp) {
        this.isUp = isUp;
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


}
