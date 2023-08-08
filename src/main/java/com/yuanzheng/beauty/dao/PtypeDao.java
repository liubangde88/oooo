package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.PtypeDo;
import com.yuanzheng.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PtypeDao {

    PtypeDo get(Long id);

    int count(Query query);

    List<PtypeDo> list(Query query);

    int update(PtypeDo ptype);

    int save(PtypeDo ptype);

    List<PtypeDo> listAll();

    int remove(Long id);
}
