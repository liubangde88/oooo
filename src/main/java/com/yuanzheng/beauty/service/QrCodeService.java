package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.QrCodeDo;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.util.List;

public interface QrCodeService {

    PageDO<QrCodeDo> queryList(Query query);

    QrCodeDo get(Long id);

    int update(QrCodeDo qrcode);

    int save(QrCodeDo qrcode);

    int remove(Long id);

    List<QrCodeDo> getListQrcode();

}
