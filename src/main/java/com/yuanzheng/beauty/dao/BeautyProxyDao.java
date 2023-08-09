package com.yuanzheng.beauty.dao;


import com.yuanzheng.beauty.domain.BeautyProxyDo;

import java.util.List;
import java.util.Map;

public interface BeautyProxyDao {

    List<BeautyProxyDo> list(Map<String, Object> map);

    int deleteByPrimaryKey(Integer id);

    int insert(BeautyProxyDo record);

    int insertSelective(BeautyProxyDo record);

    BeautyProxyDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BeautyProxyDo record);

    int updateByPrimaryKey(BeautyProxyDo record);
}