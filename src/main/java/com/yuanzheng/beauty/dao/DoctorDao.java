package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.DoctorDo;
import com.yuanzheng.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DoctorDao {

    DoctorDo get(Long id);

    int count(Query query);

    List<DoctorDo> list(Query query);

    int update(DoctorDo doctor);

    int save(DoctorDo doctor);

    List<DoctorDo> listAll();
}
