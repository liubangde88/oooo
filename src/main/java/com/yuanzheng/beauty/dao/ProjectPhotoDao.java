package com.yuanzheng.beauty.dao;

import com.yuanzheng.beauty.domain.ProjectPhotoDo;
import com.yuanzheng.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectPhotoDao {

    int count(Query query);

    List<ProjectPhotoDo> list(Query query);

    void save(ProjectPhotoDo photo);

    int remove(Long id);

    List<ProjectPhotoDo> getProjectPhoto(Long projectId);

}
