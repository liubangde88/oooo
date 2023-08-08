package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.AgentProjectDo;
import com.yuanzheng.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AgentProjectDao {

    AgentProjectDo get(Long id);

    int update(AgentProjectDo agentProject);

    int save(AgentProjectDo agentProject);

    int count(Query query);

    List<Map<String, String>> list(Query query);

    List<Map<String, String>> getListMyProject(Map<String, Object> param);

    AgentProjectDo getAgentProject(Map<String, Object> param);

    Double getMyTotal(Long agentId);

    AgentProjectDo getAgentProjectByPid(Long projectId);

    AgentProjectDo getUnCheckProject();
}
