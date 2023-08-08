package com.yuanzheng.beauty.controller;

import com.yuanzheng.beauty.domain.NoticeDo;
import com.yuanzheng.beauty.service.NoticeService;
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
@RequestMapping("/beauty/notice")
public class NoticeController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;


    @GetMapping()
    @RequiresPermissions("beauty:notice:notice")
    String Customer() {
        return "beauty/notice/notice";
    }

    @GetMapping("/add/")
    @RequiresPermissions("beauty:notice:add")
    ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("beauty/notice/add");
        return mv;
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("beauty:notice:edit")
    ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        NoticeDo notice = noticeService.get(id);
        mv.addObject("notice", notice);
        mv.setViewName("beauty/notice/edit");
        return mv;
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("beauty:notice:notice")
    PageDO<NoticeDo> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<NoticeDo> page = noticeService.queryList(query);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return page;
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("beauty:notice:edit")
    public R update(NoticeDo notice) {
        noticeService.update(notice);
        logger.info("操作者:" + getUserId() + "  修改：" + notice.getId());
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("beauty:notice:add")
    public R save(NoticeDo notice) {
        notice.setCreateTime(new Date());
        noticeService.save(notice);
        logger.info("操作者:" + getUserId() + "  新增：" + notice.getId());
        return R.ok();
    }

    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("beauty:notice:remove")
    public R remove(Long id) {
        if (noticeService.remove(id) > 0) {
            logger.info("操作者:" + getUserId() + "  删除：" + id);
            return R.ok();
        }
        return R.error();
    }
}
