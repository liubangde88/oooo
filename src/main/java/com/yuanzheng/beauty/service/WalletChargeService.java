package com.yuanzheng.beauty.service;

import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.util.Map;

public interface WalletChargeService {

    PageDO<Map<String, String>> queryList(Query query);

    int remove(Long id);

}
