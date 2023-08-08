package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.QrCodeDao;
import com.yuanzheng.beauty.domain.QrCodeDo;
import com.yuanzheng.beauty.service.QrCodeService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Autowired
    private QrCodeDao qrCodeDao;

    @Override
    public PageDO<QrCodeDo> queryList(Query query) {
        int total = qrCodeDao.count(query);
        List<QrCodeDo> ls = qrCodeDao.list(query);
        PageDO<QrCodeDo> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public QrCodeDo get(Long id) {
        return qrCodeDao.get(id);
    }

    @Override
    public int update(QrCodeDo qrcode) {
        return qrCodeDao.update(qrcode);
    }

    @Override
    public int save(QrCodeDo qrcode) {
        return qrCodeDao.save(qrcode);
    }

    @Override
    public int remove(Long id) {
        return qrCodeDao.remove(id);
    }

    @Override
    public List<QrCodeDo> getListQrcode() {
        return qrCodeDao.getListQrcode();
    }
}
