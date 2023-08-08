package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.util.Date;

public class ProjectPhotoDo implements Serializable {

    private static final long serialVersionUID = -7472707814993762713L;


    private Long id;


    private Long projectId;


    private String photoUrl;


    private Date createTime;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getProjectId() {
        return projectId;
    }


    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }


    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
