package com.yuanzheng.beauty.controller;

import com.yuanzheng.beauty.domain.ProjectPhotoDo;
import com.yuanzheng.beauty.service.ProjectPhotoService;
import com.yuanzheng.common.controller.BaseController;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.Query;
import com.yuanzheng.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/beauty/project/photo")
public class ProjectPhotoController extends BaseController {

    @Autowired
    private ProjectPhotoService projectPhotoService;

    @GetMapping("/{id}")
    ModelAndView photo(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("projectId", id);
        mv.setViewName("beauty/project/photo");
        return mv;
    }

    @GetMapping("/add/{id}")
    ModelAndView add2(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("projectId", id);
        mv.setViewName("beauty/project/upload");
        return mv;
    }

    @ResponseBody
    @GetMapping("/list")
    PageDO<ProjectPhotoDo> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<ProjectPhotoDo> page = projectPhotoService.queryList(query);
        return page;
    }

    @ResponseBody
    @RequestMapping("/save")
    public R save(ProjectPhotoDo photo) {
        projectPhotoService.save(photo);
        return R.ok();
    }

    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        if (projectPhotoService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
