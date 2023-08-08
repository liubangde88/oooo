package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.AgentWalletDo;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import com.yuanzheng.common.utils.R;

import java.util.Map;

public interface AgentWalletService {

    AgentWalletDo get(Long id);

    PageDO<Map<String, String>> queryList(Query query);

    void update(AgentWalletDo wallet);

    void save(AgentWalletDo wallet);

    AgentWalletDo getWalletByAgent(Long agentId);

    R freeze(AgentWalletDo wallet);

    void recharge(AgentWalletDo wallet);

}
