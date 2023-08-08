package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.ProjectDo;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.util.List;
import java.util.Map;

public interface ProjectService {


    ProjectDo get(Long id);

    PageDO<Map<String, String>> queryList(Query query);

    int update(ProjectDo project);

    int save(ProjectDo project);

    int pass(Long id);

    int deny(Long id);

    List<Map<String, String>> getListProject(Map<String, Object> param);

    Map<String, String> getProjectDetail(Long projectId);
}
