package com.yuanzheng.beauty.domain;

import java.io.Serializable;
import java.util.Date;


public class EmailLogDo implements Serializable {

    private static final long serialVersionUID = -7895107659137459740L;


    private Long id;


    private String email;


    private String randomCode;


    private Date sendTime;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getRandomCode() {
        return randomCode;
    }


    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }


    public Date getSendTime() {
        return sendTime;
    }


    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

}
