package com.yuanzheng.api;

import com.yuanzheng.beauty.domain.AgentDo;
import com.yuanzheng.beauty.domain.AgentWalletDo;
import com.yuanzheng.beauty.domain.BeautyProxyDo;
import com.yuanzheng.beauty.domain.EmailLogDo;
import com.yuanzheng.beauty.service.AgentService;
import com.yuanzheng.beauty.service.AgentWalletService;
import com.yuanzheng.beauty.service.BeautyProxyService;
import com.yuanzheng.beauty.service.EmailLogService;
import com.yuanzheng.common.domain.DictDO;
import com.yuanzheng.common.domain.PageDO;
import com.yuanzheng.common.service.DictService;
import com.yuanzheng.common.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/agent")
@Api(value = "/api/agent", description = "代理接口")
public class ApiAgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentWalletService agentWalletService;

    @Autowired
    private DictService dictService;

    @Autowired
    private EmailLogService emailLogService;

    @Autowired
    private BeautyProxyService beautyProxyService;

    @PostMapping(value = "/sendEmail")
    @ResponseBody
    @ApiOperation(value = "发送邮件", httpMethod = "POST")
    public R sendEmail(@ApiParam(value = "mobile") @RequestParam String email) {
        AgentDo agent = agentService.getAgentByMobile(email);
        if (null != agent) {
            return R.error("账号已经注册!");
        }
        String randomcode = getRandomString(6);

        EmailLogDo log = new EmailLogDo();
        log.setEmail(email);
        log.setRandomCode(randomcode);
        log.setSendTime(new Date());
        emailLogService.save(log);

        if (SendEmailUtil.SendEmail("Yatai999888@outlook.com", "qwe999888", email, randomcode)) {
            return R.ok();
        } else if (SendEmailUtil.SendEmail("Yatai9988@outlook.com", "qwe999888", email, randomcode)) {
            return R.ok();
        } else {
            return R.error("邮箱发送失败");
        }
    }

    @PostMapping(value = "/agentRegist")
    @ResponseBody
    @ApiOperation(value = "代理注册", httpMethod = "POST")
    public R agentRegist(@ApiParam(value = "mobile") @RequestParam String mobile,
                         @ApiParam(value = "pwd") @RequestParam String pwd,
                         @ApiParam(value = "randomCode") @RequestParam String randomCode,
                         @ApiParam(value = "invite") @RequestParam(required = false) String invite) {
        AgentDo agent = agentService.getAgentByMobile(mobile);
        if (null != agent) {
            return R.error("账号已经注册!");
        }
        EmailLogDo log = emailLogService.getLatestEmail(mobile);
        if (null == log) {
            return R.error("请先发送验证码!");
        }
        if (!log.getRandomCode().equals(randomCode)) {
            return R.error("验证码错误!");
        }
        agent = new AgentDo();
        if (StringUtils.isNoneBlank(invite)) {
            AgentDo inviteAgent = agentService.getAgentByMobile(invite);
            if (null != inviteAgent) {
                agent.setUpAgent(inviteAgent.getId());
            } else {
                agent.setUpAgent(0L);
            }
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("type", "is_regist_need_check");
        List<DictDO> dictList = dictService.list(map);
        if (dictList.size() > 0) {
            if ("0".equals(dictList.get(0).getValue())) {
                agent.setAgentStatus(1);
            }
        } else {
            agent.setAgentStatus(0);
        }
        agent.setMobile(mobile);
        agent.setLoginPwd(MD5Utils.md5Pwd(pwd));
        agent.setCreateTime(new Date());
        agentService.save(agent);

        AgentWalletDo wallet = new AgentWalletDo();
        wallet.setAgentId(agent.getId());
        wallet.setSupMoney(BigDecimal.ZERO);
        wallet.setWithMoney(BigDecimal.ZERO);
        wallet.setFreezeMoney(BigDecimal.ZERO);
        wallet.setCreateTime(new Date());
        agentWalletService.save(wallet);
        return R.ok();
    }

    @PostMapping(value = "/login")
    @ResponseBody
    @ApiOperation(value = "登陆", httpMethod = "POST")
    public R login(@ApiParam(value = "mobile") @RequestParam String mobile,
                   @ApiParam(value = "pwd") @RequestParam String pwd) {
        AgentDo agent = agentService.getAgentByMobile(mobile);
        if (null == agent) {
            return R.error("账号未注册!");
        }
        if (!agent.getLoginPwd().equals(MD5Utils.md5Pwd(pwd))) {
            return R.error("密码错误!");
        }
        if (agent.getAgentStatus() != 1) {
            return R.error("账号审核未通过或者待审核!");
        }
//		if(1==agent.getIsLogin()) {
//			return R.error("账号已在别处登陆!");
//		}
        agent.setLoginTime(new Date());
        agent.setIsLogin(1);
        agentService.update(agent);
        return R.ok().put("agent", agent);
    }

    @PostMapping(value = "/logout")
    @ResponseBody
    @ApiOperation(value = "退出登陆", httpMethod = "POST")
    public R logout(
            @ApiParam(value = "agentId") @RequestParam Long agentId
    ) {
        AgentDo agent = agentService.get(agentId);
        agent.setIsLogin(0);
        agentService.update(agent);
        return R.ok();
    }

    @PostMapping(value = "/getDetail")
    @ResponseBody
    @ApiOperation(value = "获取个人信息", httpMethod = "POST")
    public R getDetail(
            @ApiParam(value = "agentId") @RequestParam Long agentId
    ) {
        AgentDo agent = agentService.get(agentId);

        return R.ok().put("agent", agent);

    }

    @PostMapping(value = "/updateAgent")
    @ResponseBody
    @ApiOperation(value = "修改个人信息", httpMethod = "POST")
    public R updateAgent(
            @ApiParam(value = "agentId") @RequestParam Long agentId,
            @ApiParam(value = "nickName") @RequestParam(required = false) String nickName,
            @ApiParam(value = "headImg") @RequestParam(required = false) String headImg,
            @ApiParam(value = "loginPwd") @RequestParam(required = false) String loginPwd,
            @ApiParam(value = "withPwd") @RequestParam(required = false) String withPwd,
            @ApiParam(value = "withType") @RequestParam(required = false) String withType,
            @ApiParam(value = "withName") @RequestParam(required = false) String withName,
            @ApiParam(value = "withMan") @RequestParam(required = false) String withMan,
            @ApiParam(value = "withCount") @RequestParam(required = false) String withCount,
            @ApiParam(value = "withAddress") @RequestParam(required = false) String withAddress,
            @ApiParam(value = "certType") @RequestParam(required = false) String certType,
            @ApiParam(value = "certName") @RequestParam(required = false) String certName,
            @ApiParam(value = "certNum") @RequestParam(required = false) String certNum
    ) {
        AgentDo agent = agentService.get(agentId);
        if (StringUtils.isNoneBlank(nickName)) {
            agent.setNickName(nickName);
        }
        if (StringUtils.isNoneBlank(headImg)) {
            agent.setHeadImg(headImg);
        }
        if (StringUtils.isNoneBlank(loginPwd)) {
            agent.setLoginPwd(MD5Utils.md5Pwd(loginPwd));
        }
        if (StringUtils.isNoneBlank(withPwd)) {
            agent.setWithPwd(MD5Utils.md5Pwd(withPwd));
        }
        if (StringUtils.isNoneBlank(withType)) {
            agent.setWithType(withType);
        }
        if (StringUtils.isNoneBlank(withName)) {
            agent.setWithName(withName);
        }
        if (StringUtils.isNoneBlank(withMan)) {
            agent.setWithMan(withMan);
        }
        if (StringUtils.isNoneBlank(withCount)) {
            agent.setWithCount(withCount);
        }
        if (StringUtils.isNoneBlank(withAddress)) {
            agent.setWithAddress(withAddress);
        }
        if (StringUtils.isNoneBlank(certType)) {
            agent.setCertType(certType);
        }
        if (StringUtils.isNoneBlank(certName)) {
            agent.setCertName(certName);
        }
        if (StringUtils.isNoneBlank(certNum)) {
            agent.setCertNum(certNum);
        }
        agentService.update(agent);

        return R.ok();
    }

    private String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


    @PostMapping(value = "/getVipList")
    @ResponseBody
    @ApiOperation(value = "获取会员列表以及个人信息", httpMethod = "POST")
    public R getVipList(@RequestParam Long agentId) {
        // 获取用户信息
        AgentDo userInfo = agentService.get(agentId);

        // 获取代理列表
        Map<String, Object> where = new HashMap<>();
        where.put("status", 1);
        List<BeautyProxyDo> proxyList = beautyProxyService.getList(where);

        // 获取当前用户有效下级会员人数
        AgentDo agentdo = new AgentDo();
        agentdo.setUpAgent(agentId);
        List<AgentDo> list = agentService.getList(agentdo);

        return R.ok().put("userInfo", userInfo).put("list", list);
    }
}
