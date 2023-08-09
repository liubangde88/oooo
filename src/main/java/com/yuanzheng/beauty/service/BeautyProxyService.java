package com.yuanzheng.beauty.service;


import com.yuanzheng.beauty.domain.BeautyProxyDo;

import java.util.List;
import java.util.Map;

/**
 * @author liu
 * @description 针对表【beauty_proxy】的数据库操作Service
 * @createDate 2023-08-07 23:54:46
 */
public interface BeautyProxyService {

    List<BeautyProxyDo> getList(Map<String, Object> map);

    int save(BeautyProxyDo data);


    int updateByPrimaryKeySelective(BeautyProxyDo data);

    BeautyProxyDo selectByPrimaryKey(int proxyId);
}
