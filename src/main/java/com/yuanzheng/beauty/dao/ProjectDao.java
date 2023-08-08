package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.ProjectDo;
import com.yuanzheng.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectDao {

    ProjectDo get(Long id);

    int count(Query query);

    List<Map<String, String>> list(Query query);

    int update(ProjectDo project);

    int save(ProjectDo project);

    List<Map<String, String>> getListProject(Map<String, Object> param);

    Map<String, String> getProjectDetail(Long projectId);
}
