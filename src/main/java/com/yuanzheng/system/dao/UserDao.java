package com.yuanzheng.system.dao;

import com.yuanzheng.system.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserDao {

    UserDO get(Long userId);

    List<UserDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserDO user);

    int update(UserDO user);

    int remove(Long userId);

    int batchRemove(Long[] userIds);

    Long[] listAllDept(Map<String, Object> query);

    List<UserDO> listAllUser();

    List<UserDO> getListUserByDeptId(Long valueOf);

    UserDO getByName(String userName);

}
