package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.AttDao;
import com.yuanzheng.beauty.domain.AttDo;
import com.yuanzheng.beauty.service.AttService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttServiceImpl implements AttService {


    @Autowired
    private AttDao attDao;

    @Override
    public AttDo get(Long id) {
        return attDao.get(id);
    }

    @Override
    public PageDO<AttDo> queryList(Query query) {
        int total = attDao.count(query);
        List<AttDo> ls = attDao.list(query);
        PageDO<AttDo> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public void update(AttDo att) {
        attDao.update(att);
    }

    @Override
    public void save(AttDo att) {
        attDao.save(att);
    }

    @Override
    public int remove(Long id) {
        return attDao.remove(id);
    }

    @Override
    public List<AttDo> getListAtt() {
        return attDao.getListAtt();
    }

}
