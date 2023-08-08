package com.yuanzheng.beauty.controller;

import com.yuanzheng.beauty.domain.AgentWalletDo;
import com.yuanzheng.beauty.service.AgentWalletService;
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
@RequestMapping("/beauty/agent/wallet")
public class AgentWalletController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(AgentWalletController.class);

    @Autowired
    private AgentWalletService agentWalletService;

    @GetMapping()
    @RequiresPermissions("beauty:wallet:wallet")
    String Customer() {
        return "beauty/wallet/wallet";
    }


    @GetMapping("/freeze/{id}")
    @RequiresPermissions("beauty:wallet:edit")
    ModelAndView freeze(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        AgentWalletDo wallet = agentWalletService.get(id);
        mv.addObject("wallet", wallet);
        mv.setViewName("beauty/wallet/freeze");
        return mv;
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("beauty:wallet:edit")
    ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        AgentWalletDo wallet = agentWalletService.get(id);
        mv.addObject("wallet", wallet);
        mv.setViewName("beauty/wallet/edit");
        return mv;
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("beauty:wallet:wallet")
    PageDO<Map<String, String>> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<Map<String, String>> page = agentWalletService.queryList(query);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return page;
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("beauty:wallet:edit")
    public R update(AgentWalletDo wallet) {
        agentWalletService.recharge(wallet);
        logger.info("操作者:" + getUserId() + "  充值：" + wallet.getId());
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/freeze")
    @RequiresPermissions("beauty:wallet:edit")
    public R freeze(AgentWalletDo wallet) {
        return agentWalletService.freeze(wallet);
    }
}
