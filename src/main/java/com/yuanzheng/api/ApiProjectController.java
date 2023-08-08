package com.yuanzheng.api;

import com.yuanzheng.beauty.domain.*;
import com.yuanzheng.beauty.service.*;
import com.yuanzheng.common.utils.R;
import com.yuanzheng.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
@Api(value = "/api/project", description = "项目接口")
public class ApiProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectPhotoService projectPhotoService;

    @Autowired
    private PtypeService ptypeService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentProjectService agentProjectService;

    @Autowired
    private AgentWalletService agentWalletService;

    @ResponseBody
    @GetMapping("/type/list/")
    @ApiOperation(value = "获取项目类型", httpMethod = "GET")
    public List<PtypeDo> listByType() {
        // 查询列表数据
        List<PtypeDo> dictList = ptypeService.listAll();
        return dictList;
    }

    @GetMapping(value = "/getListProject")
    @ResponseBody
    @ApiOperation(value = "获取项目列表", httpMethod = "GET")
    public R getListProject(
            @ApiParam(name = "search", value = "search") @RequestParam(required = false) String search,
            @ApiParam(name = "type", value = "type") @RequestParam(required = false) String type,
            @ApiParam(name = "page", value = "page") @RequestParam Integer page,
            @ApiParam(name = "pagesize", value = "pagesize") @RequestParam Integer pagesize
    ) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("offset", (page - 1) * pagesize);
        param.put("limit", pagesize);
        param.put("isUp", "1");
        if (StringUtils.isNotBlank(search)) {
            param.put("search", search);
        }
        if (StringUtils.isNotBlank(type)) {
            param.put("type", type);
        }
        List<Map<String, String>> ls = projectService.getListProject(param);
        return R.ok().put("ls", ls);
    }

    @GetMapping(value = "/getProjectDetail")
    @ResponseBody
    @ApiOperation(value = "获取项目详情", httpMethod = "GET")
    public R getProjectDetail(
            @ApiParam(name = "projectId", value = "projectId") @RequestParam Long projectId
    ) {
        return R.ok().put("project", projectService.getProjectDetail(projectId));
    }

    @GetMapping(value = "/getProjectPhoto")
    @ResponseBody
    @ApiOperation(value = "获取项目图片", httpMethod = "GET")
    public R getProjectPhoto(
            @ApiParam(name = "projectId", value = "projectId") @RequestParam Long projectId
    ) {
        List<ProjectPhotoDo> ls = projectPhotoService.getProjectPhoto(projectId);
        return R.ok().put("ls", ls);
    }

    @GetMapping(value = "/getListMyProject")
    @ResponseBody
    @ApiOperation(value = "获取我的项目", httpMethod = "GET")
    public R getListMyProject(
            @ApiParam(name = "agentId", value = "agentId") @RequestParam Long agentId,
            @ApiParam(name = "status", value = "-1审核拒绝0未审核1审核通过2已结算") @RequestParam(required = false) String status,
            @ApiParam(name = "page", value = "page") @RequestParam Integer page,
            @ApiParam(name = "pagesize", value = "pagesize") @RequestParam Integer pagesize
    ) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("offset", (page - 1) * pagesize);
        param.put("limit", pagesize);
        param.put("agentId", agentId);
        if (StringUtils.isNotBlank(status)) {
            param.put("status", status);
        }
        List<Map<String, String>> ls = agentProjectService.getListMyProject(param);
        return R.ok().put("ls", ls);
    }

    @GetMapping(value = "/getMyTotal")
    @ResponseBody
    @ApiOperation(value = "获取佣金总额", httpMethod = "GET")
    public R getMyTotal(
            @ApiParam(name = "agentId", value = "agentId") @RequestParam Long agentId
    ) {
        BigDecimal total = agentProjectService.getMyTotal(agentId);
        return R.ok().put("total", total);
    }

    @PostMapping(value = "/addProject")
    @ResponseBody
    @ApiOperation(value = "预约项目", httpMethod = "POST")
    public R addProject(
            @ApiParam(name = "agentId", value = "agentId") @RequestParam Long agentId,
            @ApiParam(name = "projectId", value = "projectId") @RequestParam Long projectId
    ) {
        AgentDo agent = agentService.get(agentId);
        if (null == agent || agent.getAgentStatus() != 1) {
            return R.error("请先登陆或者等待管理员审核!");
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("agentId", agentId);
        param.put("projectId", projectId);
        AgentProjectDo agentProject = agentProjectService.getAgentProject(param);
        if (null != agentProject) {
            return R.error("已经预约过!");
        }

        AgentProjectDo ap = agentProjectService.getAgentProjectByPid(projectId);
        if (null != ap) {
            return R.error("项目已被预约");
        }

        ProjectDo project = projectService.get(projectId);

        AgentWalletDo wallet = agentWalletService.getWalletByAgent(agentId);
        if (wallet.getSupMoney().compareTo(project.getPrice()) < 0) {
            return R.error("钱包余额不足!");
        }
        project.setIsUp(0);
        projectService.update(project);


        agentProject = new AgentProjectDo();
        agentProject.setAgentId(agentId);
        agentProject.setCreateTime(new Date());
        agentProject.setStatus(0);
        agentProject.setProjectId(projectId);
        agentProjectService.save(agentProject);

        wallet.setSupMoney(wallet.getSupMoney().subtract(project.getPrice()));
        wallet.setFreezeMoney(wallet.getFreezeMoney().add(project.getPrice()));
        agentWalletService.update(wallet);

        return R.ok();
    }
}
