package com.yuanzheng.beauty.controller;

import com.yuanzheng.beauty.domain.AgentProjectDo;
import com.yuanzheng.beauty.domain.AgentWithDo;
import com.yuanzheng.beauty.service.AgentProjectService;
import com.yuanzheng.beauty.service.AgentWithService;
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

import java.util.Map;

@Controller
@RequestMapping("/beauty/agent/project")
public class AgentProjectController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(AgentProjectController.class);

    @Autowired
    private AgentProjectService agentProjectService;

    @Autowired
    private AgentWithService agentWithService;

    @GetMapping()
    @RequiresPermissions("beauty:agent:project")
    String Customer() {
        return "beauty/agentp/agentp";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("beauty:agent:project")
    PageDO<Map<String, String>> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<Map<String, String>> page = agentProjectService.queryList(query);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return page;
    }

    @PostMapping("/pass")
    @ResponseBody
    @RequiresPermissions("beauty:agent:project:check")
    public R pass(Long id) {
        if (agentProjectService.pass(id) > 0) {
            logger.info("操作者:" + getUserId() + "  审核通过：" + id);
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/deny")
    @ResponseBody
    @RequiresPermissions("beauty:agent:project:check")
    public R deny(Long id) {
        if (agentProjectService.deny(id) > 0) {
            logger.info("操作者:" + getUserId() + "  审核拒绝：" + id);
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/pay")
    @ResponseBody
    @RequiresPermissions("beauty:agent:project:check")
    public R pay(Long id) {
        if (agentProjectService.pay(id) > 0) {
            logger.info("操作者:" + getUserId() + "  结算：" + id);
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/check")
    @ResponseBody
    public R check() {
        AgentProjectDo agentProject = agentProjectService.getUnCheckProject();
        AgentWithDo with = agentWithService.getUnCheckWith();
        if (null != agentProject || null != with) {
            return R.ok();
        }
        return R.error();
    }
}
