package com.yuanzheng.beauty.controller;

import com.yuanzheng.beauty.domain.PtypeDo;
import com.yuanzheng.beauty.service.PtypeService;
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
@RequestMapping("/beauty/ptype")
public class PtypeController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(PtypeController.class);


    @Autowired
    private PtypeService ptypeService;

    @GetMapping()
    @RequiresPermissions("beauty:ptype:ptype")
    String Customer() {
        return "beauty/ptype/ptype";
    }

    @GetMapping("/add/")
    @RequiresPermissions("beauty:ptype:add")
    ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("beauty/ptype/add");
        return mv;
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("beauty:ptype:edit")
    ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        PtypeDo ptype = ptypeService.get(id);
        mv.addObject("ptype", ptype);
        mv.setViewName("beauty/ptype/edit");
        return mv;
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("beauty:ptype:ptype")
    PageDO<PtypeDo> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<PtypeDo> page = ptypeService.queryList(query);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return page;
    }

    @ResponseBody
    @GetMapping("/select")
    List<PtypeDo> select() {
        List<PtypeDo> list = ptypeService.listAll();

        return list;
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("beauty:ptype:edit")
    public R update(PtypeDo ptype) {
        ptypeService.update(ptype);
        logger.info("操作者:" + getUserId() + "  修改：" + ptype.getId());
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("beauty:ptype:add")
    public R save(PtypeDo ptype) {
        ptypeService.save(ptype);
        logger.info("操作者:" + getUserId() + "  新增：" + ptype.getId());
        return R.ok();
    }

    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("beauty:ptype:remove")
    public R remove(Long id) {
        if (ptypeService.remove(id) > 0) {
            logger.info("操作者:" + getUserId() + "  删除：" + id);
            return R.ok();
        }
        return R.error();
    }
}
