package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WalletChargeDo implements Serializable {

    private static final long serialVersionUID = -1760521287009497539L;

    private Long id;

    private Long agentId;

    private int type;

    private BigDecimal money;

    private BigDecimal fsupMoney;

    private BigDecimal bsupMoney;

    private Date createTime;

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

    public BigDecimal getFsupMoney() {
        return fsupMoney;
    }

    public void setFsupMoney(BigDecimal fsupMoney) {
        this.fsupMoney = fsupMoney;
    }

    public BigDecimal getBsupMoney() {
        return bsupMoney;
    }

    public void setBsupMoney(BigDecimal bsupMoney) {
        this.bsupMoney = bsupMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
