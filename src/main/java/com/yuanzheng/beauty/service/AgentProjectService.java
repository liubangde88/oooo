package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.AgentProjectDo;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface AgentProjectService {

    AgentProjectDo get(Long id);

    PageDO<Map<String, String>> queryList(Query query);

    int update(AgentProjectDo agentProject);

    int save(AgentProjectDo agentProject);

    int pass(Long id);

    int deny(Long id);

    int pay(Long id);

    List<Map<String, String>> getListMyProject(Map<String, Object> param);

    AgentProjectDo getAgentProject(Map<String, Object> param);

    BigDecimal getMyTotal(Long agentId);

    AgentProjectDo getAgentProjectByPid(Long projectId);

    AgentProjectDo getUnCheckProject();
}
