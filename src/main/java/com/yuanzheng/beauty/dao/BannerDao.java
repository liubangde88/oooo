package com.yuanzheng.beauty.dao;


import com.yuanzheng.beauty.domain.BannerDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * @author wang
 * @email 1992lcg@163.com
 * @date 2021-06-08 15:27:15
 */
@Mapper
public interface BannerDao {

    BannerDO get(Long id);

    int count(Map<String, Object> map);

    int fuzzyQueryCount(Map<String, Object> map);

    List<BannerDO> list(Map<String, Object> map);

    List<BannerDO> fuzzyQueryList(Map<String, Object> map);

    List<Map<String, Object>> findDatas(Map<String, Object> map);

    int save(BannerDO banner);

    int update(BannerDO banner);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
