package com.yuanzheng.beauty.controller;

import com.yuanzheng.beauty.domain.QrCodeDo;
import com.yuanzheng.beauty.service.QrCodeService;
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
@RequestMapping("/beauty/qrcode")
public class QrCodeController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(QrCodeController.class);

    @Autowired
    private QrCodeService qrCodeService;

    @GetMapping()
    @RequiresPermissions("beauty:qrcode:qrcode")
    String Customer() {
        return "beauty/qrcode/qrcode";
    }

    @GetMapping("/add/")
    @RequiresPermissions("beauty:qrcode:add")
    ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("beauty/qrcode/add");
        return mv;
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("beauty:qrcode:edit")
    ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        QrCodeDo qrcode = qrCodeService.get(id);
        mv.addObject("qrcode", qrcode);
        mv.setViewName("beauty/qrcode/edit");
        return mv;
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("beauty:qrcode:qrcode")
    PageDO<QrCodeDo> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<QrCodeDo> page = qrCodeService.queryList(query);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return page;
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("beauty:qrcode:edit")
    public R update(QrCodeDo qrcode) {
        qrCodeService.update(qrcode);
        logger.info("操作者:" + getUserId() + "  修改：" + qrcode.getId());
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("beauty:qrcode:add")
    public R save(QrCodeDo qrcode) {
        qrcode.setCreateTime(new Date());
        qrCodeService.save(qrcode);
        logger.info("操作者:" + getUserId() + "  新增：" + qrcode.getId());
        return R.ok();
    }

    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("beauty:qrcode:remove")
    public R remove(Long id) {
        if (qrCodeService.remove(id) > 0) {
            logger.info("操作者:" + getUserId() + "  删除：" + id);
            return R.ok();
        }
        return R.error();
    }
}
