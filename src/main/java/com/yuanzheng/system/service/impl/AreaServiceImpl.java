package com.yuanzheng.system.service.impl;

import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import com.yuanzheng.system.dao.AreaDao;
import com.yuanzheng.system.domain.AreaDO;
import com.yuanzheng.system.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public AreaDO get(Long id) {
        return areaDao.get(id);
    }

    @Override
    public PageDO<AreaDO> queryList(Query query) {
        int total = areaDao.fuzzyQueryCount(query);
        List<AreaDO> ls = areaDao.fuzzyQueryList(query);
        PageDO<AreaDO> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public List<AreaDO> list(Map<String, Object> map) {
        return areaDao.list(map);
    }

    @Override
    public List<AreaDO> fuzzyQueryList(Map<String, Object> map) {
        return areaDao.fuzzyQueryList(map);
    }


    @Override
    public List<Map<String, Object>> findDatas(Map<String, Object> map) {
        List<Map<String, Object>> list = areaDao.findDatas(map);
        return list;
    }

    @Override
    public int save(AreaDO area) {
        return areaDao.save(area);
    }

    @Override
    public int update(AreaDO area) {
        return areaDao.update(area);
    }

    @Override
    public int remove(Long id) {
        return areaDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return areaDao.batchRemove(ids);
    }

    @Override
    public int count(Map<String, Object> map) {
        return areaDao.count(map);
    }

    @Override
    public List<AreaDO> getAreaByPid(Long pid) {
        return areaDao.getAreaByPid(pid);
    }

}
