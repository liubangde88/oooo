package com.yuanzheng.common.config;

public class MqMessage {

    //0短信，1拼团
    private Integer messageType = 0;

    private String data;

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
