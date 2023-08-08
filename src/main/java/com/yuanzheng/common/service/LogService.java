package com.yuanzheng.common.service;


import com.yuanzheng.common.domain.LogDO;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.stereotype.Service;

@Service
public interface LogService {
    void save(LogDO logDO);

    PageDO<LogDO> queryList(Query query);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
