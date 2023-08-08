package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.EmailLogDo;

public interface EmailLogService {

    int save(EmailLogDo log);

    EmailLogDo getLatestEmail(String mobile);

}
