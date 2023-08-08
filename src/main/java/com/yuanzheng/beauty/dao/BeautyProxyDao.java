package com.yuanzheng.beauty.dao;


import com.yuanzheng.beauty.domain.BeautyProxyDo;

import java.util.List;
import java.util.Map;

public interface BeautyProxyDao {

    List<BeautyProxyDo> list(Map<String, Object> map);

    int save(BeautyProxyDo data);
}