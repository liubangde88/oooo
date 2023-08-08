package com.yuanzheng.beauty.controller;

import com.yuanzheng.beauty.domain.AttDo;
import com.yuanzheng.beauty.service.AttService;
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

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/beauty/att")
public class AttController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(AttController.class);

    @Autowired
    private AttService attService;

    @GetMapping()
    @RequiresPermissions("beauty:att:att")
    String Customer() {
        return "beauty/att/att";
    }

    @GetMapping("/add/")
    @RequiresPermissions("beauty:att:add")
    ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("beauty/att/add");
        return mv;
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("beauty:att:edit")
    ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        AttDo att = attService.get(id);
        mv.addObject("att", att);
        mv.setViewName("beauty/att/edit");
        return mv;
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("beauty:att:att")
    PageDO<AttDo> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<AttDo> page = attService.queryList(query);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return page;
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("beauty:att:edit")
    public R update(AttDo att) {
        attService.update(att);
        logger.info("操作者:" + getUserId() + "  修改：" + att.getId());
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("beauty:att:add")
    public R save(AttDo att) {
        att.setCreateTime(new Date());
        attService.save(att);
        logger.info("操作者:" + getUserId() + "  新增：" + att.getId());
        return R.ok();
    }

    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("beauty:att:remove")
    public R remove(Long id) {
        if (attService.remove(id) > 0) {
            logger.info("操作者:" + getUserId() + "  删除：" + id);
            return R.ok();
        }
        return R.error();
    }
}
