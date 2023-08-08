package com.yuanzheng.system.controller;

import com.yuanzheng.common.controller.BaseController;
import com.yuanzheng.common.utils.R;
import com.yuanzheng.system.domain.AreaDO;
import com.yuanzheng.system.service.AreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author wang
 * @date 2021-06-11 18:22:04
 */

@Controller
@RequestMapping("/system/area")
public class AreaController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(AreaController.class);
    @Autowired
    private AreaService areaService;

    @GetMapping()
    @RequiresPermissions("system:area:area")
    String Area() {
        return "system/area/area";
    }

    /**
     * 分页列表查询
     */
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:area:area")
    List<AreaDO> list(@RequestParam Map<String, Object> params) {
        List<AreaDO> list = areaService.list(params);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return list;
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add/{pid}")
    @RequiresPermissions("system:area:add")
    ModelAndView add(@PathVariable("pid") Long pid) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("area", new AreaDO());
        mv.addObject("pid", pid);
        mv.setViewName("system/area/add");
        return mv;
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:area:edit")
    ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        AreaDO area = areaService.get(id);
        mv.addObject("area", area);
        mv.setViewName("system/area/edit");
        return mv;
    }

    /**
     * 新增方法
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:area:add")
    public R save(AreaDO area) {
        if (areaService.save(area) > 0) {
            logger.info("操作者:" + getUserId() + "  新增：");

            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改方法
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:area:edit")
    public R update(AreaDO area) {
        areaService.update(area);
        logger.info("操作者:" + getUserId() + "  修改：" + area.getId());

        return R.ok();
    }

    /**
     * 单条删除方法
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:area:remove")
    public R remove(Long id) {
        if (areaService.remove(id) > 0) {
            logger.info("操作者:" + getUserId() + "  删除：" + id);
            return R.ok();
        }
        return R.error();
    }

    /**
     * 批量删除方法
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:area:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        areaService.batchRemove(ids);
        logger.info("操作者:" + getUserId() + "  批量删除：" + ids);

        return R.ok();
    }

}
