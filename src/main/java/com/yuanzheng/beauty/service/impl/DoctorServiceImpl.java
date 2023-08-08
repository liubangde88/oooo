package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.DoctorDao;
import com.yuanzheng.beauty.domain.DoctorDo;
import com.yuanzheng.beauty.service.DoctorService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    @Override
    public DoctorDo get(Long id) {
        return doctorDao.get(id);
    }

    @Override
    public PageDO<DoctorDo> queryList(Query query) {
        int total = doctorDao.count(query);
        List<DoctorDo> ls = doctorDao.list(query);
        PageDO<DoctorDo> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public int update(DoctorDo doctor) {
        doctor.setUpdateTime(new Date());
        return doctorDao.update(doctor);
    }

    @Override
    public int save(DoctorDo doctor) {
        doctor.setCreateTime(new Date());
        return doctorDao.save(doctor);
    }

    @Override
    public List<DoctorDo> listAll() {
        return doctorDao.listAll();
    }

}
