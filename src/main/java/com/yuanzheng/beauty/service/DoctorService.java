package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.DoctorDo;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.util.List;

public interface DoctorService {

    DoctorDo get(Long id);

    PageDO<DoctorDo> queryList(Query query);

    int update(DoctorDo doctor);

    int save(DoctorDo doctor);

    List<DoctorDo> listAll();

}
