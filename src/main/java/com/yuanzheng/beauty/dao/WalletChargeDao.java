package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.WalletChargeDo;
import com.yuanzheng.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WalletChargeDao {

    int count(Query query);

    List<Map<String, String>> list(Query query);

    void save(WalletChargeDo walletCharge);

    int remove(Long id);

    WalletChargeDo get(Long id);
}
