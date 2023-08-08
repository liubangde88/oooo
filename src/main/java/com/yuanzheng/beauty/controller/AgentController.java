package com.yuanzheng.beauty.controller;

import com.yuanzheng.beauty.domain.AgentDo;
import com.yuanzheng.beauty.service.AgentService;
import com.yuanzheng.common.controller.BaseController;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.utils.MD5Utils;
import com.yuanzheng.common.utils.Query;
import com.yuanzheng.common.utils.R;
import com.yuanzheng.common.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
@RequestMapping("/beauty/agent")
public class AgentController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(AgentController.class);

    @Autowired
    private AgentService agentService;


    @GetMapping()
    @RequiresPermissions("beauty:agent:agent")
    String Customer() {
        return "beauty/agent/agent";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("beauty:agent:edit")
    ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        AgentDo agent = agentService.get(id);
        mv.addObject("agent", agent);
        mv.setViewName("beauty/agent/edit");
        return mv;
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("beauty:agent:agent")
    PageDO<AgentDo> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<AgentDo> page = agentService.queryList(query);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return page;
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("beauty:agent:edit")
    public R update(AgentDo agent) {
        if (StringUtils.isNoneBlank(agent.getLoginPwd())) {
            agent.setLoginPwd(MD5Utils.md5Pwd(agent.getLoginPwd()));
        } else {
            agent.setLoginPwd(null);
        }
        if (StringUtils.isNoneBlank(agent.getWithPwd())) {
            agent.setWithPwd(MD5Utils.md5Pwd(agent.getWithPwd()));
        } else {
            agent.setWithPwd(null);
        }
        agentService.update(agent);
        logger.info("操作者:" + getUserId() + "  修改：" + agent.getId());
        return R.ok();
    }

    @PostMapping("/pass")
    @ResponseBody
    @RequiresPermissions("beauty:agent:check")
    public R pass(Long id) {
        if (agentService.pass(id) > 0) {
            logger.info("操作者:" + getUserId() + "  审核通过：" + id);
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/deny")
    @ResponseBody
    @RequiresPermissions("beauty:agent:check")
    public R deny(Long id) {
        if (agentService.deny(id) > 0) {
            logger.info("操作者:" + getUserId() + "  审核拒绝：" + id);
            return R.ok();
        }
        return R.error();
    }
}
