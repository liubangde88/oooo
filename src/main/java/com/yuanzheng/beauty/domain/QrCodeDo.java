package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.util.Date;

public class QrCodeDo implements Serializable {

    private static final long serialVersionUID = -6343362401663201473L;


    private Long id;


    private String qrUrl;


    private String qrName;


    private String qrAccount;


    private String qrAddress;


    private Date createTime;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getQrUrl() {
        return qrUrl;
    }


    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }


    public String getQrName() {
        return qrName;
    }


    public void setQrName(String qrName) {
        this.qrName = qrName;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getQrAccount() {
        return qrAccount;
    }


    public void setQrAccount(String qrAccount) {
        this.qrAccount = qrAccount;
    }


    public String getQrAddress() {
        return qrAddress;
    }


    public void setQrAddress(String qrAddress) {
        this.qrAddress = qrAddress;
    }


}
