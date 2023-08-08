package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.NoticeDo;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    NoticeDo get(Long id);

    PageDO<NoticeDo> queryList(Query query);

    void update(NoticeDo notice);

    void save(NoticeDo notice);

    int remove(Long id);

    List<NoticeDo> getListNotice(Map<String, Object> param);

}
