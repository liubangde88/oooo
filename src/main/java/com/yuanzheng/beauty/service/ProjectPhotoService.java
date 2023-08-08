package com.yuanzheng.beauty.service;

import com.yuanzheng.beauty.domain.ProjectPhotoDo;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;

import java.util.List;

public interface ProjectPhotoService {

    PageDO<ProjectPhotoDo> queryList(Query query);

    void save(ProjectPhotoDo photo);

    int remove(Long id);

    List<ProjectPhotoDo> getProjectPhoto(Long projectId);

}
