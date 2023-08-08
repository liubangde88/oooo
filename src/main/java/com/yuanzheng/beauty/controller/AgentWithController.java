package com.yuanzheng.beauty.controller;

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
@RequestMapping("/beauty/agent/with")
public class AgentWithController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(AgentWithController.class);

    @Autowired
    private AgentWithService agentWithService;

    @GetMapping()
    @RequiresPermissions("beauty:with:with")
    String Customer() {
        return "beauty/with/with";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("beauty:with:with")
    PageDO<Map<String, String>> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<Map<String, String>> page = agentWithService.queryList(query);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return page;
    }

    @PostMapping("/pass")
    @ResponseBody
    @RequiresPermissions("beauty:with:check")
    public R pass(Long id) {
        if (agentWithService.pass(id) > 0) {
            logger.info("操作者:" + getUserId() + "  审核通过：" + id);
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/deny")
    @ResponseBody
    @RequiresPermissions("beauty:with:check")
    public R deny(Long id) {
        if (agentWithService.deny(id) > 0) {
            logger.info("操作者:" + getUserId() + "  审核拒绝：" + id);
            return R.ok();
        }
        return R.error();
    }
}
