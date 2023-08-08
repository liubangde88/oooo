package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.NoticeDo;
import com.yuanzheng.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeDao {

    NoticeDo get(Long id);

    int count(Query query);

    List<NoticeDo> list(Query query);

    int update(NoticeDo notice);

    int save(NoticeDo notice);

    int remove(Long id);

    List<NoticeDo> getListNotice(Map<String, Object> param);
}
