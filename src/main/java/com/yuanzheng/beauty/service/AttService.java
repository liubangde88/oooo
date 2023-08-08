package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.AttDo;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.util.List;

public interface AttService {

    AttDo get(Long id);

    PageDO<AttDo> queryList(Query query);

    void update(AttDo att);

    void save(AttDo att);

    int remove(Long id);

    List<AttDo> getListAtt();

}
