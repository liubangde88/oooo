package com.yuanzheng.beauty.service;


import com.yuanzheng.beauty.domain.BannerDO;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * @author wang
 * @email 1992lcg@163.com
 * @date 2021-06-08 15:27:15
 */
public interface BannerService {

    BannerDO get(Long id);

    PageDO<BannerDO> queryList(Query query);

    List<BannerDO> list(Map<String, Object> map);

    List<BannerDO> fuzzyQueryList(Map<String, Object> map);


    List<Map<String, Object>> findDatas(Map<String, Object> map);

    int save(BannerDO banner);

    int update(BannerDO banner);

    int remove(Long id);

    int batchRemove(Long[] ids);

    int count(Map<String, Object> map);

}
