package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.PtypeDao;
import com.yuanzheng.beauty.domain.PtypeDo;
import com.yuanzheng.beauty.service.PtypeService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PtypeServiceImpl implements PtypeService {

    @Autowired
    private PtypeDao ptypeDao;

    @Override
    public PtypeDo get(Long id) {
        return ptypeDao.get(id);
    }

    @Override
    public PageDO<PtypeDo> queryList(Query query) {
        int total = ptypeDao.count(query);
        List<PtypeDo> ls = ptypeDao.list(query);
        PageDO<PtypeDo> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public List<PtypeDo> listAll() {
        return ptypeDao.listAll();
    }

    @Override
    public void update(PtypeDo ptype) {
        ptypeDao.update(ptype);
    }

    @Override
    public void save(PtypeDo ptype) {
        ptype.setCreateTime(new Date());
        ptypeDao.save(ptype);
    }

    @Override
    public int remove(Long id) {
        return ptypeDao.remove(id);
    }

}
