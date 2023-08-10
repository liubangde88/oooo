package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.BeautyProxyDao;
import com.yuanzheng.beauty.domain.BeautyProxyDo;
import com.yuanzheng.beauty.service.BeautyProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author liu
 * @description 针对表【beauty_proxy】的数据库操作Service实现
 * @createDate 2023-08-07 23:54:46
 */
@Service
public class BeautyProxyServiceImpl implements BeautyProxyService {

    @Autowired
    BeautyProxyDao beautyProxyDao;

    @Override
    public List<BeautyProxyDo> getList(Map<String, Object> map) {
        return beautyProxyDao.list(map);
    }

    @Override
    public int save(BeautyProxyDo data) {
        return beautyProxyDao.insert(data);
    }

    @Override
    public int updateByPrimaryKeySelective(BeautyProxyDo beautyProxyDo) {
        return beautyProxyDao.updateByPrimaryKeySelective(beautyProxyDo);
    }

    @Override
    public BeautyProxyDo selectByPrimaryKey(int proxyId) {
        return beautyProxyDao.selectByPrimaryKey(proxyId);
    }

    @Override
    public int deleteByPrimaryKey(int id) {
        return beautyProxyDao.deleteByPrimaryKey(id);
    }
}




