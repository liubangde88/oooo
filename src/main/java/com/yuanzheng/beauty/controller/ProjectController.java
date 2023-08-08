package com.yuanzheng.beauty.controller;

import com.yuanzheng.beauty.domain.ProjectDo;
import com.yuanzheng.beauty.service.ProjectService;
import com.yuanzheng.common.controller.BaseController;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import com.yuanzheng.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/beauty/project")
public class ProjectController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @GetMapping()
    @RequiresPermissions("beauty:project:project")
    String Customer() {
        return "beauty/project/project";
    }

    @GetMapping("/add/")
    @RequiresPermissions("beauty:project:add")
    ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("beauty/project/add");
        return mv;
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("beauty:project:edit")
    ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        ProjectDo project = projectService.get(id);
        mv.addObject("project", project);
        mv.setViewName("beauty/project/edit");
        return mv;
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("beauty:project:project")
    PageDO<Map<String, String>> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<Map<String, String>> page = projectService.queryList(query);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return page;
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("beauty:project:edit")
    public R update(ProjectDo project) {
        projectService.update(project);
        logger.info("操作者:" + getUserId() + "  修改：" + project.getId());
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("beauty:project:add")
    public R save(ProjectDo project) {
        projectService.save(project);
        logger.info("操作者:" + getUserId() + "  新增：" + project.getId());
        return R.ok();
    }

    @PostMapping("/pass")
    @ResponseBody
    @RequiresPermissions("beauty:project:check")
    public R pass(Long id) {
        if (projectService.pass(id) > 0) {
            logger.info("操作者:" + getUserId() + "  发布：" + id);
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/deny")
    @ResponseBody
    @RequiresPermissions("beauty:project:check")
    public R deny(Long id) {
        if (projectService.deny(id) > 0) {
            logger.info("操作者:" + getUserId() + "  下架：" + id);
            return R.ok();
        }
        return R.error();
    }
}
