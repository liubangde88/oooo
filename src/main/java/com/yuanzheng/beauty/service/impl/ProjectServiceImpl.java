package com.yuanzheng.beauty.service.impl;

import com.yuanzheng.beauty.dao.ProjectDao;
import com.yuanzheng.beauty.domain.ProjectDo;
import com.yuanzheng.beauty.service.ProjectService;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public ProjectDo get(Long id) {
        return projectDao.get(id);
    }

    @Override
    public PageDO<Map<String, String>> queryList(Query query) {
        int total = projectDao.count(query);
        List<Map<String, String>> ls = projectDao.list(query);
        PageDO<Map<String, String>> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(ls);
        return page;
    }

    @Override
    public int update(ProjectDo project) {
        return projectDao.update(project);
    }

    @Override
    public int save(ProjectDo project) {
        project.setCreateTime(new Date());
        project.setIsUp(0);
        return projectDao.save(project);
    }

    @Override
    public int pass(Long id) {
        ProjectDo project = projectDao.get(id);
        project.setIsUp(1);
        project.setUpTime(new Date());
        return projectDao.update(project);
    }

    @Override
    public int deny(Long id) {
        ProjectDo project = projectDao.get(id);
        project.setIsUp(0);
        project.setUpTime(new Date());
        return projectDao.update(project);
    }

    @Override
    public List<Map<String, String>> getListProject(Map<String, Object> param) {
        return projectDao.getListProject(param);
    }

    @Override
    public Map<String, String> getProjectDetail(Long projectId) {
        return projectDao.getProjectDetail(projectId);
    }
}
