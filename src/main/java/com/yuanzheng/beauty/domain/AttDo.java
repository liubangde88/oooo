package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.util.Date;

public class AttDo implements Serializable {


    private static final long serialVersionUID = -26913382050676736L;


    private Long id;


    private String attUrl;


    private String attName;


    private Date createTime;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getAttUrl() {
        return attUrl;
    }


    public void setAttUrl(String attUrl) {
        this.attUrl = attUrl;
    }


    public String getAttName() {
        return attName;
    }


    public void setAttName(String attName) {
        this.attName = attName;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
