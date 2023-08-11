package com.yuanzheng.api;

import com.yuanzheng.beauty.domain.AgentDo;
import com.yuanzheng.beauty.domain.AgentWalletDo;
import com.yuanzheng.beauty.domain.AgentWithDo;
import com.yuanzheng.beauty.domain.WalletChargeDo;
import com.yuanzheng.beauty.service.AgentService;
import com.yuanzheng.beauty.service.AgentWalletService;
import com.yuanzheng.beauty.service.AgentWithService;
import com.yuanzheng.beauty.service.WalletChargeService;
import com.yuanzheng.common.controller.BaseController;
import com.yuanzheng.common.utils.MD5Utils;
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
@RequestMapping("/api/wallet")
@Api(value = "/api/wallet", description = "钱包")
public class ApiWalletController extends BaseController {

    @Autowired
    private AgentWalletService agentWalletService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentWithService agentWithService;

    @Autowired
    private WalletChargeService walletChargeService;


    @GetMapping(value = "/getWallet")
    @ResponseBody
    @ApiOperation(value = "获取钱包详情", httpMethod = "GET")
    public R getWallet(
            @ApiParam(value = "agentId") @RequestParam Long agentId
    ) {
        AgentWalletDo wallet = agentWalletService.getWalletByAgent(agentId);
        return R.ok().put("wallet", wallet);
    }

    @PostMapping(value = "/checkWithPwd")
    @ResponseBody
    @ApiOperation(value = "验证提现密码", httpMethod = "POST")
    public R checkWithPwd(
            @ApiParam(value = "agentId") @RequestParam Long agentId,
            @ApiParam(value = "withPwd") @RequestParam String withPwd
    ) {
        AgentDo agent = agentService.get(agentId);
        if (StringUtils.isBlank(agent.getWithPwd())) {
            return R.error("提现密码未设置");
        }
        if (!MD5Utils.md5Pwd(withPwd).equals(agent.getWithPwd())) {
            return R.error("提现密码错误");
        }
        return R.ok();
    }

    @PostMapping(value = "/withdraw")
    @ResponseBody
    @ApiOperation(value = "提现", httpMethod = "POST")
    public R withdraw(
            @ApiParam(value = "agentId") @RequestParam Long agentId,
            @ApiParam(value = "withMoney") @RequestParam BigDecimal withMoney
    ) {
        AgentWalletDo wallet = agentWalletService.getWalletByAgent(agentId);
        if (wallet.getSupMoney().compareTo(withMoney) < 0) {
            return R.error("提现余额不足");
        }
        wallet.setSupMoney(wallet.getSupMoney().subtract(withMoney));

        AgentDo agent = agentService.get(agentId);
        AgentWithDo with = new AgentWithDo();
        with.setAgentId(agentId);
        with.setCreateTime(new Date());
        with.setSupMoney(wallet.getSupMoney());
        with.setWithType(agent.getWithType());
        with.setWithName(with.getWithName());
        with.setWithMan(agent.getWithMan());
        with.setWithCount(agent.getWithCount());
        with.setWithMoney(withMoney);
        with.setWithType(agent.getWithType());
        with.setWithStatus(0);
        with.setCreateTime(new Date());
        agentWithService.save(with);

        agentWalletService.update(wallet);
        return R.ok();
    }

    @GetMapping(value = "/getListWithdraw")
    @ResponseBody
    @ApiOperation(value = "获取提现记录", httpMethod = "GET")
    public R getListWithdraw(
            @ApiParam(name = "agentId", value = "agentId") @RequestParam Long agentId,
            @ApiParam(name = "page", value = "page") @RequestParam Integer page,
            @ApiParam(name = "pagesize", value = "pagesize") @RequestParam Integer pagesize
    ) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("agentId", agentId);
        param.put("offset", (page - 1) * pagesize);
        param.put("limit", pagesize);

        List<AgentWithDo> ls = agentWithService.getListWithdraw(param);
        return R.ok().put("ls", ls);
    }

    @PostMapping(value = "/getRechargeList")
    @ResponseBody
    @ApiOperation(value = "获取充值记录", httpMethod = "POST")
    public R getRechargeList(@RequestParam Long agentId) {
        System.out.println(agentId);
        WalletChargeDo walletChargeDo = new WalletChargeDo();
        walletChargeDo.setAgentId(agentId);

        List<WalletChargeDo> list = walletChargeService.getList(walletChargeDo);

        return R.ok().put("list", list);
    }
}
