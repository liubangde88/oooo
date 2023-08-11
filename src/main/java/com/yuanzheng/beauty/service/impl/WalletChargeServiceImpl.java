package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.AgentWalletDao;
import com.yuanzheng.beauty.dao.WalletChargeDao;
import com.yuanzheng.beauty.domain.AgentWalletDo;
import com.yuanzheng.beauty.domain.WalletChargeDo;
import com.yuanzheng.beauty.service.WalletChargeService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WalletChargeServiceImpl implements WalletChargeService {

    @Autowired
    private WalletChargeDao walletChargeDao;

    @Autowired
    private AgentWalletDao agentWalletDao;

    @Override
    public PageDO<Map<String, String>> queryList(Query query) {
        int total = walletChargeDao.count(query);
        List<Map<String, String>> ls = walletChargeDao.list(query);
        PageDO<Map<String, String>> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public int remove(Long id) {
        WalletChargeDo walletChargeDo = walletChargeDao.get(id);
        AgentWalletDo wallet = agentWalletDao.getWallet(walletChargeDo.getAgentId());
        if (0 == walletChargeDo.getType()) {
            wallet.setSupMoney(wallet.getSupMoney().subtract(walletChargeDo.getMoney()));
            wallet.setUpdateTime(new Date());
            agentWalletDao.update(wallet);
        }
        if (1 == walletChargeDo.getType()) {
            wallet.setSupMoney(wallet.getSupMoney().add(walletChargeDo.getMoney()));
            wallet.setFreezeMoney(wallet.getFreezeMoney().subtract(walletChargeDo.getMoney()));
            wallet.setUpdateTime(new Date());
        }
        agentWalletDao.update(wallet);
        return walletChargeDao.remove(id);
    }

    @Override
    public List<WalletChargeDo> getList(WalletChargeDo walletChargeDo) {
        return walletChargeDao.getList(walletChargeDo);
    }



}
