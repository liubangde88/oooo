package com.yuanzheng.system.dao;

import com.yuanzheng.system.domain.DeptTreeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wang
 * @email 1992lcg@163.com
 * @date 2021-06-03 15:28:21
 */
@Mapper
public interface DeptTreeDao {

    int save(DeptTreeDO deptTree);

    int remove(Long id);

}
