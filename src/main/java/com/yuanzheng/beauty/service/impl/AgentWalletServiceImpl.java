package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.AgentWalletDao;
import com.yuanzheng.beauty.dao.WalletChargeDao;
import com.yuanzheng.beauty.domain.AgentWalletDo;
import com.yuanzheng.beauty.domain.WalletChargeDo;
import com.yuanzheng.beauty.service.AgentWalletService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import com.yuanzheng.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AgentWalletServiceImpl implements AgentWalletService {

    @Autowired
    private AgentWalletDao agentWalletDao;

    @Autowired
    private WalletChargeDao walletChargeDao;

    @Override
    public AgentWalletDo get(Long id) {
        return agentWalletDao.get(id);
    }

    @Override
    public PageDO<Map<String, String>> queryList(Query query) {
        int total = agentWalletDao.count(query);
        List<Map<String, String>> ls = agentWalletDao.list(query);
        PageDO<Map<String, String>> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public void update(AgentWalletDo wallet) {
        wallet.setUpdateTime(new Date());
        agentWalletDao.update(wallet);
    }

    @Override
    public void save(AgentWalletDo wallet) {
        agentWalletDao.save(wallet);
    }

    @Override
    public AgentWalletDo getWalletByAgent(Long agentId) {
        return agentWalletDao.getWallet(agentId);
    }

    @Override
    public R freeze(AgentWalletDo wallet) {
        AgentWalletDo g = agentWalletDao.get(wallet.getId());
        if (g.getSupMoney().compareTo(wallet.getFreezeMoney()) < 0) {
            return R.error("余额不足");
        }
        WalletChargeDo charge = new WalletChargeDo();
        charge.setAgentId(g.getAgentId());
        charge.setFsupMoney(g.getSupMoney());
        charge.setMoney(wallet.getFreezeMoney());
        charge.setType(1);
        charge.setCreateTime(new Date());

        g.setFreezeMoney(g.getFreezeMoney().add(wallet.getFreezeMoney()));
        g.setSupMoney(g.getSupMoney().subtract(wallet.getFreezeMoney()));
        g.setUpdateTime(new Date());
        agentWalletDao.update(g);

        charge.setBsupMoney(g.getSupMoney());

        walletChargeDao.save(charge);
        return R.ok();
    }

    @Override
    public void recharge(AgentWalletDo wallet) {
        AgentWalletDo g = agentWalletDao.get(wallet.getId());
        WalletChargeDo charge = new WalletChargeDo();
        charge.setAgentId(g.getAgentId());
        charge.setFsupMoney(g.getSupMoney());
        charge.setMoney(wallet.getSupMoney());
        charge.setType(0);
        charge.setCreateTime(new Date());
        g.setSupMoney(g.getSupMoney().add(wallet.getSupMoney()));
        g.setUpdateTime(new Date());
        charge.setBsupMoney(g.getSupMoney());

        walletChargeDao.save(charge);
        agentWalletDao.update(g);
    }

}
