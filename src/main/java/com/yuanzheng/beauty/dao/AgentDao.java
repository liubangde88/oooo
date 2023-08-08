package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.AgentDo;
import com.yuanzheng.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AgentDao {

    AgentDo get(Long id);

    int count(Query query);

    List<AgentDo> list(Query query);

    int update(AgentDo agent);

    AgentDo getByMobile(String invite);

    int save(AgentDo agent);

    int getAllCount();
}
