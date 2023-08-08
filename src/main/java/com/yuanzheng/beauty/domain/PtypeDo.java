package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.util.Date;

public class PtypeDo implements Serializable {

    private static final long serialVersionUID = 7033793098822000471L;

    private Long id;

    private String name;

    private String value;

    private String cover;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
