package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.AgentWithDo;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.util.List;
import java.util.Map;

public interface AgentWithService {

    PageDO<Map<String, String>> queryList(Query query);

    int pass(Long id);

    int deny(Long id);

    int save(AgentWithDo with);

    List<AgentWithDo> getListWithdraw(Map<String, Object> param);

    AgentWithDo getUnCheckWith();

}
