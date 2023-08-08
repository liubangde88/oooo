package com.yuanzheng.beauty.controller;

import com.yuanzheng.beauty.domain.DoctorDo;
import com.yuanzheng.beauty.service.DoctorService;
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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/beauty/doctor")
public class DoctorController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private DoctorService doctorService;

    @GetMapping()
    @RequiresPermissions("beauty:doctor:doctor")
    String Customer() {
        return "beauty/doctor/doctor";
    }

    @GetMapping("/add/")
    @RequiresPermissions("beauty:doctor:add")
    ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("beauty/doctor/add");
        return mv;
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("beauty:doctor:edit")
    ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        DoctorDo doctor = doctorService.get(id);
        mv.addObject("doctor", doctor);
        mv.setViewName("beauty/doctor/edit");
        return mv;
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("beauty:doctor:doctor")
    PageDO<DoctorDo> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<DoctorDo> page = doctorService.queryList(query);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return page;
    }

    @ResponseBody
    @GetMapping("/select")
    List<DoctorDo> select() {
        List<DoctorDo> list = doctorService.listAll();

        return list;
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("beauty:doctor:edit")
    public R update(DoctorDo doctor) {
        doctorService.update(doctor);
        logger.info("操作者:" + getUserId() + "  修改：" + doctor.getId());
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("beauty:doctor:add")
    public R save(DoctorDo doctor) {
        doctorService.save(doctor);
        logger.info("操作者:" + getUserId() + "  新增：" + doctor.getId());
        return R.ok();
    }
}
