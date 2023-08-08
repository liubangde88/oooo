package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.NoticeDao;
import com.yuanzheng.beauty.domain.NoticeDo;
import com.yuanzheng.beauty.service.NoticeService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public NoticeDo get(Long id) {
        return noticeDao.get(id);
    }

    @Override
    public PageDO<NoticeDo> queryList(Query query) {
        int total = noticeDao.count(query);
        List<NoticeDo> ls = noticeDao.list(query);
        PageDO<NoticeDo> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public void update(NoticeDo notice) {
        noticeDao.update(notice);
    }

    @Override
    public void save(NoticeDo notice) {
        noticeDao.save(notice);
    }

    @Override
    public int remove(Long id) {
        return noticeDao.remove(id);
    }

    @Override
    public List<NoticeDo> getListNotice(Map<String, Object> param) {
        return noticeDao.getListNotice(param);
    }

}
