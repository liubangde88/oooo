package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.AgentDao;
import com.yuanzheng.beauty.domain.AgentDo;
import com.yuanzheng.beauty.service.AgentService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentDao agentDao;

    @Override
    public AgentDo get(Long id) {
        return agentDao.get(id);
    }

    @Override
    public PageDO<AgentDo> queryList(Query query) {
        int total = agentDao.count(query);
        List<AgentDo> ls = agentDao.list(query);
        PageDO<AgentDo> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public void update(AgentDo agent) {
        agentDao.update(agent);
    }

    @Override
    public int pass(Long id) {
        AgentDo agent = agentDao.get(id);
        agent.setAgentStatus(1);

        return agentDao.update(agent);
    }

    @Override
    public int deny(Long id) {
        AgentDo agent = agentDao.get(id);
        agent.setAgentStatus(-1);

        return agentDao.update(agent);
    }

    @Override
    public AgentDo getAgentByMobile(String invite) {
        return agentDao.getByMobile(invite);
    }

    @Override
    public int save(AgentDo agent) {
        return agentDao.save(agent);
    }

    @Override
    public int count(Query query) {
        return agentDao.count(query);
    }

    @Override
    public int getAllCount() {
        return agentDao.getAllCount();
    }

    @Override
    public List<AgentDo> getList(AgentDo agentDo) {
        return agentDao.getList(agentDo);
    }

}
