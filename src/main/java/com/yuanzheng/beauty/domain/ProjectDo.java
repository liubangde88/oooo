package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProjectDo implements Serializable {

    private static final long serialVersionUID = 4490135365059995974L;


    private Long id;


    private Long doctorId;


    private String type;


    private String name;


    private String content;


    private BigDecimal price;


    private BigDecimal agentPercent;


    private String detail;


    private int isUp;


    private Date createTime;


    private Date upTime;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getDoctorId() {
        return doctorId;
    }


    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public BigDecimal getAgentPercent() {
        return agentPercent;
    }


    public void setAgentPercent(BigDecimal agentPercent) {
        this.agentPercent = agentPercent;
    }


    public String getDetail() {
        return detail;
    }


    public void setDetail(String detail) {
        this.detail = detail;
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


    public Date getUpTime() {
        return upTime;
    }


    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }


}
