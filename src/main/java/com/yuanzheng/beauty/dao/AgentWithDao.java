package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.AgentWithDo;
import com.yuanzheng.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AgentWithDao {

    int count(Query query);

    List<Map<String, String>> list(Query query);

    AgentWithDo get(Long id);

    int update(AgentWithDo with);

    int save(AgentWithDo with);

    List<AgentWithDo> getListWithdraw(Map<String, Object> param);

    AgentWithDo getUnCheckWith();
}
