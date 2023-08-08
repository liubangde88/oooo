package com.yuanzheng.system.dao;

import com.yuanzheng.system.domain.AreaDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-05-25 09:40:02
 */
@Mapper
public interface AreaDao {

    AreaDO get(Long id);

    int count(Map<String, Object> map);

    int fuzzyQueryCount(Map<String, Object> map);

    List<AreaDO> list(Map<String, Object> map);

    List<AreaDO> fuzzyQueryList(Map<String, Object> map);

    List<Map<String, Object>> findDatas(Map<String, Object> map);

    int save(AreaDO area);

    int update(AreaDO area);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<AreaDO> getAreaByPid(Long pid);
}
