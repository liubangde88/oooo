package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.util.Date;

public class AgentProjectDo implements Serializable {

    private static final long serialVersionUID = -5419898369571579271L;


    private Long id;


    private Long projectId;


    private Long agentId;


    private int status;


    private Date createTime;


    private Date checkTime;


    private Date payTime;


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


    public Long getAgentId() {
        return agentId;
    }


    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }


    public int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getCheckTime() {
        return checkTime;
    }


    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }


    public Date getPayTime() {
        return payTime;
    }


    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

}
