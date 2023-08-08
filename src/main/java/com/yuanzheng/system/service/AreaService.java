package com.yuanzheng.system.service;

import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import com.yuanzheng.system.domain.AreaDO;

import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-05-25 09:40:02
 */
public interface AreaService {

    AreaDO get(Long id);

    PageDO<AreaDO> queryList(Query query);

    List<AreaDO> list(Map<String, Object> map);

    List<AreaDO> fuzzyQueryList(Map<String, Object> map);


    List<Map<String, Object>> findDatas(Map<String, Object> map);

    int save(AreaDO area);

    int update(AreaDO area);

    int remove(Long id);

    int batchRemove(Long[] ids);

    int count(Map<String, Object> map);

    List<AreaDO> getAreaByPid(Long pid);

}
