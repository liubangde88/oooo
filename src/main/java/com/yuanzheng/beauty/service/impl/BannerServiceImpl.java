package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.BannerDao;
import com.yuanzheng.beauty.domain.BannerDO;
import com.yuanzheng.beauty.service.BannerService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public BannerDO get(Long id) {
        return bannerDao.get(id);
    }

    @Override
    public PageDO<BannerDO> queryList(Query query) {
        int total = bannerDao.fuzzyQueryCount(query);
        List<BannerDO> ls = bannerDao.fuzzyQueryList(query);
        PageDO<BannerDO> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public List<BannerDO> list(Map<String, Object> map) {
        return bannerDao.list(map);
    }

    @Override
    public List<BannerDO> fuzzyQueryList(Map<String, Object> map) {
        return bannerDao.fuzzyQueryList(map);
    }


    @Override
    public List<Map<String, Object>> findDatas(Map<String, Object> map) {
        List<Map<String, Object>> list = bannerDao.findDatas(map);
        return list;
    }

    @Override
    public int save(BannerDO banner) {
        banner.setCreateTime(new Date());
        return bannerDao.save(banner);
    }

    @Override
    public int update(BannerDO banner) {
        banner.setUpdateTime(new Date());
        return bannerDao.update(banner);
    }

    @Override
    public int remove(Long id) {
        return bannerDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return bannerDao.batchRemove(ids);
    }

    @Override
    public int count(Map<String, Object> map) {
        return bannerDao.count(map);
    }

}
