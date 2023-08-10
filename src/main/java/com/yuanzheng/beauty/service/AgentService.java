package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.AgentDo;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.util.List;

public interface AgentService {

    AgentDo get(Long id);

    PageDO<AgentDo> queryList(Query query);

    void update(AgentDo agent);

    int pass(Long id);

    int deny(Long id);

    AgentDo getAgentByMobile(String invite);

    int save(AgentDo agent);

    int count(Query query);

    int getAllCount();


    List<AgentDo> getList(AgentDo map);
}
