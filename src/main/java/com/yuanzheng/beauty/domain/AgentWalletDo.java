package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AgentWalletDo implements Serializable {

    private static final long serialVersionUID = -8442780828658851179L;

    private Long id;


    private Long agentId;


    private BigDecimal supMoney;


    private BigDecimal withMoney;


    private BigDecimal freezeMoney;


    private Date createTime;


    private Date updateTime;


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


    public BigDecimal getSupMoney() {
        return supMoney;
    }


    public void setSupMoney(BigDecimal supMoney) {
        this.supMoney = supMoney;
    }


    public BigDecimal getWithMoney() {
        return withMoney;
    }


    public void setWithMoney(BigDecimal withMoney) {
        this.withMoney = withMoney;
    }


    public BigDecimal getFreezeMoney() {
        return freezeMoney;
    }


    public void setFreezeMoney(BigDecimal freezeMoney) {
        this.freezeMoney = freezeMoney;
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
