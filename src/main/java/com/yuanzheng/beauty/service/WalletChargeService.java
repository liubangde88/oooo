package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.WalletChargeDo;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.util.List;
import java.util.Map;

public interface WalletChargeService {

    PageDO<Map<String, String>> queryList(Query query);

    int remove(Long id);

    List<WalletChargeDo> getList(WalletChargeDo walletChargeDo);
}
