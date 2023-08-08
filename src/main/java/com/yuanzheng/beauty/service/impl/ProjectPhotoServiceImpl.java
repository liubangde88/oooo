package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.ProjectPhotoDao;
import com.yuanzheng.beauty.domain.ProjectPhotoDo;
import com.yuanzheng.beauty.service.ProjectPhotoService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectPhotoServiceImpl implements ProjectPhotoService {

    @Autowired
    private ProjectPhotoDao projectPhotoDao;

    @Override
    public PageDO<ProjectPhotoDo> queryList(Query query) {
        int total = projectPhotoDao.count(query);
        List<ProjectPhotoDo> ls = projectPhotoDao.list(query);
        PageDO<ProjectPhotoDo> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public void save(ProjectPhotoDo photo) {
        photo.setCreateTime(new Date());
        projectPhotoDao.save(photo);
    }

    @Override
    public int remove(Long id) {
        return projectPhotoDao.remove(id);
    }

    @Override
    public List<ProjectPhotoDo> getProjectPhoto(Long projectId) {
        return projectPhotoDao.getProjectPhoto(projectId);
    }

}
