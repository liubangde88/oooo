package com.yuanzheng.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "boot")
public class BootConfig {
    //上传路径
    private String uploadPath;

    private String username;

    private String password;

    private String platPath;

    private Integer autoTask;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlatPath() {
        return platPath;
    }

    public void setPlatPath(String platPath) {
        this.platPath = platPath;
    }

    public Integer getAutoTask() {
        return autoTask;
    }

    public void setAutoTask(Integer autoTask) {
        this.autoTask = autoTask;
    }

}
