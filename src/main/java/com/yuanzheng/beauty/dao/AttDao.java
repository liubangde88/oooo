package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.AttDo;
import com.yuanzheng.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttDao {

    AttDo get(Long id);

    int count(Query query);

    List<AttDo> list(Query query);

    int update(AttDo att);

    int save(AttDo att);

    int remove(Long id);

    List<AttDo> getListAtt();
}
