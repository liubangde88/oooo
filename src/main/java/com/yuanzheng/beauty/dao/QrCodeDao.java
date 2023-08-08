package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.QrCodeDo;
import com.yuanzheng.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QrCodeDao {

    QrCodeDo get(Long id);

    int count(Query query);

    List<QrCodeDo> list(Query query);

    int update(QrCodeDo qrCode);

    int save(QrCodeDo qrCode);

    int remove(Long id);

    List<QrCodeDo> getListQrcode();
}
