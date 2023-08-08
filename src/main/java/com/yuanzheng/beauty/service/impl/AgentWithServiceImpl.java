package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.AgentWalletDao;
import com.yuanzheng.beauty.dao.AgentWithDao;
import com.yuanzheng.beauty.domain.AgentWalletDo;
import com.yuanzheng.beauty.domain.AgentWithDo;
import com.yuanzheng.beauty.service.AgentWithService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AgentWithServiceImpl implements AgentWithService {

    @Autowired
    private AgentWalletDao agentWalletDao;

    @Autowired
    private AgentWithDao agentWithDao;

    @Override
    public PageDO<Map<String, String>> queryList(Query query) {
        int total = agentWithDao.count(query);
        List<Map<String, String>> ls = agentWithDao.list(query);
        PageDO<Map<String, String>> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public int pass(Long id) {
        AgentWithDo with = agentWithDao.get(id);
        with.setWithStatus(1);
        with.setWithTime(new Date());
        AgentWalletDo wallet = agentWalletDao.get(with.getAgentId());
        wallet.setWithMoney(wallet.getWithMoney().add(with.getWithMoney()));
        agentWalletDao.update(wallet);
        return agentWithDao.update(with);
    }

    @Override
    public int deny(Long id) {
        AgentWithDo with = agentWithDao.get(id);
        with.setWithStatus(-1);
        with.setWithTime(new Date());
        AgentWalletDo wallet = agentWalletDao.get(with.getAgentId());
        wallet.setSupMoney(wallet.getSupMoney().add(with.getWithMoney()));
        agentWalletDao.update(wallet);

        return agentWithDao.update(with);
    }

    @Override
    public int save(AgentWithDo with) {
        return agentWithDao.save(with);
    }

    @Override
    public List<AgentWithDo> getListWithdraw(Map<String, Object> param) {
        return agentWithDao.getListWithdraw(param);
    }

    @Override
    public AgentWithDo getUnCheckWith() {
        return agentWithDao.getUnCheckWith();
    }

}
