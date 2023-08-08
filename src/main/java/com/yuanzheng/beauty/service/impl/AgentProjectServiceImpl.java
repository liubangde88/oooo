package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.AgentProjectDao;
import com.yuanzheng.beauty.dao.AgentWalletDao;
import com.yuanzheng.beauty.dao.ProjectDao;
import com.yuanzheng.beauty.domain.AgentProjectDo;
import com.yuanzheng.beauty.domain.AgentWalletDo;
import com.yuanzheng.beauty.domain.ProjectDo;
import com.yuanzheng.beauty.service.AgentProjectService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AgentProjectServiceImpl implements AgentProjectService {

    @Autowired
    private AgentProjectDao agentProjectDao;

    @Autowired
    private AgentWalletDao agentWalletDao;

    @Autowired
    private ProjectDao projectDao;

    @Override
    public AgentProjectDo get(Long id) {
        return agentProjectDao.get(id);
    }

    @Override
    public PageDO<Map<String, String>> queryList(Query query) {
        int total = agentProjectDao.count(query);
        List<Map<String, String>> ls = agentProjectDao.list(query);
        PageDO<Map<String, String>> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public int update(AgentProjectDo agentProject) {
        return agentProjectDao.update(agentProject);
    }

    @Override
    public int save(AgentProjectDo agentProject) {
        agentProject.setCreateTime(new Date());
        return agentProjectDao.save(agentProject);
    }

    @Override
    public int pass(Long id) {
        AgentProjectDo projectDo = agentProjectDao.get(id);
        projectDo.setStatus(1);
        projectDo.setCheckTime(new Date());
        return agentProjectDao.update(projectDo);
    }

    @Override
    public int deny(Long id) {
        AgentProjectDo projectDo = agentProjectDao.get(id);
        projectDo.setStatus(-1);
        projectDo.setCheckTime(new Date());

        ProjectDo p = projectDao.get(projectDo.getProjectId());

        AgentWalletDo wallet = agentWalletDao.getWallet(projectDo.getAgentId());
        wallet.setSupMoney(wallet.getSupMoney()
                .add(p.getPrice()));
        wallet.setFreezeMoney(wallet.getFreezeMoney().subtract(p.getPrice()));
        agentWalletDao.update(wallet);
        return agentProjectDao.update(projectDo);
    }

    @Override
    public int pay(Long id) {
        AgentProjectDo projectDo = agentProjectDao.get(id);
        projectDo.setStatus(2);
        projectDo.setPayTime(new Date());

        ProjectDo p = projectDao.get(projectDo.getProjectId());

        AgentWalletDo wallet = agentWalletDao.getWallet(projectDo.getAgentId());
        wallet.setSupMoney(wallet.getSupMoney()
                .add(p.getPrice())
                .add(p.getAgentPercent().multiply(p.getPrice())
                        .divide(new BigDecimal(100))));
        wallet.setFreezeMoney(wallet.getFreezeMoney().subtract(p.getPrice()));
        agentWalletDao.update(wallet);
        return agentProjectDao.update(projectDo);
    }

    @Override
    public List<Map<String, String>> getListMyProject(Map<String, Object> param) {
        return agentProjectDao.getListMyProject(param);
    }

    @Override
    public AgentProjectDo getAgentProject(Map<String, Object> param) {
        return agentProjectDao.getAgentProject(param);
    }

    @Override
    public BigDecimal getMyTotal(Long agentId) {
        Double total = agentProjectDao.getMyTotal(agentId);
        if (null == total) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(agentProjectDao.getMyTotal(agentId));
    }

    @Override
    public AgentProjectDo getAgentProjectByPid(Long projectId) {
        return agentProjectDao.getAgentProjectByPid(projectId);
    }

    @Override
    public AgentProjectDo getUnCheckProject() {
        return agentProjectDao.getUnCheckProject();
    }

}
