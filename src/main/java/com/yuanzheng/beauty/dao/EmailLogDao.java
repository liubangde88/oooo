package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.EmailLogDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailLogDao {


    int save(EmailLogDo emailLog);


    EmailLogDo getLatest(String email);
}
