package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.PtypeDo;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.util.List;

public interface PtypeService {

    PtypeDo get(Long id);

    PageDO<PtypeDo> queryList(Query query);

    List<PtypeDo> listAll();

    void update(PtypeDo ptype);

    void save(PtypeDo ptype);

    int remove(Long id);

}
