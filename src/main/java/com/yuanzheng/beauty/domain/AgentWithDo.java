package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AgentWithDo implements Serializable {

    private static final long serialVersionUID = -7425227496761834209L;


    private Long id;


    private Long agentId;


    private String withType;


    private String withName;

    private String withMan;

    private String withCount;


    private BigDecimal withMoney;


    private BigDecimal supMoney;


    private int withStatus;


    private Date createTime;


    private Date withTime;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getAgentId() {
        return agentId;
    }


    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }


    public String getWithType() {
        return withType;
    }


    public void setWithType(String withType) {
        this.withType = withType;
    }


    public String getWithName() {
        return withName;
    }


    public void setWithName(String withName) {
        this.withName = withName;
    }


    public String getWithCount() {
        return withCount;
    }


    public void setWithCount(String withCount) {
        this.withCount = withCount;
    }


    public BigDecimal getWithMoney() {
        return withMoney;
    }


    public void setWithMoney(BigDecimal withMoney) {
        this.withMoney = withMoney;
    }


    public BigDecimal getSupMoney() {
        return supMoney;
    }


    public void setSupMoney(BigDecimal supMoney) {
        this.supMoney = supMoney;
    }


    public int getWithStatus() {
        return withStatus;
    }


    public void setWithStatus(int withStatus) {
        this.withStatus = withStatus;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getWithTime() {
        return withTime;
    }


    public void setWithTime(Date withTime) {
        this.withTime = withTime;
    }


    public String getWithMan() {
        return withMan;
    }


    public void setWithMan(String withMan) {
        this.withMan = withMan;
    }

}
