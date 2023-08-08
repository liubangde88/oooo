package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.EmailLogDao;
import com.yuanzheng.beauty.domain.EmailLogDo;
import com.yuanzheng.beauty.service.EmailLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailLogServiceImpl implements EmailLogService {

    @Autowired
    private EmailLogDao emailLogDao;

    @Override
    public int save(EmailLogDo log) {
        return emailLogDao.save(log);
    }

    @Override
    public EmailLogDo getLatestEmail(String mobile) {
        return emailLogDao.getLatest(mobile);
    }

}
