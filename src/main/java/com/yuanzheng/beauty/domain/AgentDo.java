package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.util.Date;

public class AgentDo implements Serializable {

    private static final long serialVersionUID = 5709241547063169554L;


    private Long id;


    private Long upAgent;


    private String agentLevel;


    private String nickName;


    private String headImg;


    private String mobile;


    private String loginPwd;


    private String withPwd;


    private String withType;


    private String withName;

    private String withMan;

    private String withCount;


    private String withAddress;

    //状态-1审核不通告，0未审核，1已审核
    private Integer agentStatus;

    private Integer isLogin;

    private String certType;

    private String certName;

    private String certNum;

    private Date createTime;


    private Date loginTime;

    private String cardFrontImg;

    private String cardRsImg;

    private Integer cardStatus;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getUpAgent() {
        return upAgent;
    }


    public void setUpAgent(Long upAgent) {
        this.upAgent = upAgent;
    }


    public String getNickName() {
        return nickName;
    }


    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getHeadImg() {
        return headImg;
    }


    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }


    public String getMobile() {
        return mobile;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getLoginPwd() {
        return loginPwd;
    }


    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }


    public String getWithPwd() {
        return withPwd;
    }


    public void setWithPwd(String withPwd) {
        this.withPwd = withPwd;
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


    public Integer getAgentStatus() {
        return agentStatus;
    }


    public void setAgentStatus(Integer agentStatus) {
        this.agentStatus = agentStatus;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getLoginTime() {
        return loginTime;
    }


    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }


    public String getAgentLevel() {
        return agentLevel;
    }


    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }


    public String getWithAddress() {
        return withAddress;
    }


    public void setWithAddress(String withAddress) {
        this.withAddress = withAddress;
    }


    public Integer getIsLogin() {
        return isLogin;
    }


    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }


    public String getWithMan() {
        return withMan;
    }


    public void setWithMan(String withMan) {
        this.withMan = withMan;
    }


    public String getCertType() {
        return certType;
    }


    public void setCertType(String certType) {
        this.certType = certType;
    }


    public String getCertName() {
        return certName;
    }


    public void setCertName(String certName) {
        this.certName = certName;
    }


    public String getCertNum() {
        return certNum;
    }


    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getCardFrontImg() {
        return cardFrontImg;
    }

    public void setCardFrontImg(String cardFrontImg) {
        this.cardFrontImg = cardFrontImg;
    }

    public String getCardRsImg() {
        return cardRsImg;
    }

    public void setCardRsImg(String cardRsImg) {
        this.cardRsImg = cardRsImg;
    }

    public Integer getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Integer cardStatus) {
        this.cardStatus = cardStatus;
    }
}