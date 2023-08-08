package com.yuanzheng.beauty.controller;

import com.yuanzheng.beauty.domain.BannerDO;
import com.yuanzheng.beauty.service.BannerService;
import com.yuanzheng.common.annotation.Log;
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

/**
 * @author wang
 * @date 2021-06-08 15:27:15
 */

@Controller
@RequestMapping("/beauty/banner")
public class BannerController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(BannerController.class);
    @Autowired
    private BannerService bannerService;

    @GetMapping()
    @RequiresPermissions("beauty:banner:banner")
    String Banner() {
        return "beauty/banner/banner";
    }

    /**
     * 分页列表查询
     */
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("beauty:banner:banner")
    PageDO<BannerDO> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<BannerDO> page = bannerService.queryList(query);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return page;
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @RequiresPermissions("beauty:banner:add")
    ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("banner", new BannerDO());
        mv.setViewName("beauty/banner/add");
        return mv;
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("beauty:banner:edit")
    ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        BannerDO banner = bannerService.get(id);
        mv.addObject("banner", banner);
        mv.setViewName("beauty/banner/edit");
        return mv;
    }

    /**
     * 新增方法
     */
    @Log("新增广告")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("beauty:banner:add")
    public R save(BannerDO banner) {
        if (bannerService.save(banner) > 0) {
            logger.info("操作者:" + getUserId() + "  新增：");

            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改方法
     */
    @Log("编辑广告信息")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("beauty:banner:edit")
    public R update(BannerDO banner) {
        bannerService.update(banner);
        logger.info("操作者:" + getUserId() + "  修改：" + banner.getId());

        return R.ok();
    }

    /**
     * 单条删除方法
     */
    @Log("删除广告")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("beauty:banner:remove")
    public R remove(Long id) {
        if (bannerService.remove(id) > 0) {
            logger.info("操作者:" + getUserId() + "  删除：" + id);
            return R.ok();
        }
        return R.error();
    }

}
