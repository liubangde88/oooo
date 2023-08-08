package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.AgentWalletDo;
import com.yuanzheng.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AgentWalletDao {

    AgentWalletDo get(Long id);

    void update(AgentWalletDo wallet);

    int count(Query query);

    List<Map<String, String>> list(Query query);

    AgentWalletDo getWallet(Long agentId);

    void save(AgentWalletDo wallet);

}
