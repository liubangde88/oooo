package com.yuanzheng.system.controller;

import com.yuanzheng.beauty.service.AgentProjectService;
import com.yuanzheng.beauty.service.AgentService;
import com.yuanzheng.beauty.service.AgentWithService;
import com.yuanzheng.beauty.service.DoctorService;
import com.yuanzheng.common.annotation.Log;
import com.yuanzheng.common.config.BootConfig;
import com.yuanzheng.common.controller.BaseController;
import com.yuanzheng.common.domain.FileDO;
import com.yuanzheng.common.domain.Tree;
import com.yuanzheng.common.service.FileService;
import com.yuanzheng.common.utils.*;
import com.yuanzheng.system.domain.MenuDO;
import com.yuanzheng.system.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MenuService menuService;

    @Autowired
    FileService fileService;

    @Autowired
    BootConfig oneZorConfig;

    @Autowired
    AgentService agentService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    AgentProjectService agentProjectService;


    @Autowired
    AgentWithService agentWithService;

    @GetMapping({"/", ""})
    String welcome(Model model) {

        return "redirect:/login";
    }

    @Log("请求访问主页")
    @GetMapping({"/index"})
    String index(Model model) {
        List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("name", getUser().getName());
        FileDO fileDO = fileService.get(getUser().getPicId());
        if (fileDO != null && fileDO.getUrl() != null) {
            if (fileService.isExist(fileDO.getUrl())) {
                model.addAttribute("picUrl", fileDO.getUrl());
            } else {
                model.addAttribute("picUrl", "/img/photo_s.jpg");
            }
        } else {
            model.addAttribute("picUrl", "/img/photo_s.jpg");
        }
        model.addAttribute("username", getUser().getUsername());
        return "index_v1";
    }

    @GetMapping("/login")
    String login(Model model) {
        model.addAttribute("username", oneZorConfig.getUsername());
        model.addAttribute("password", oneZorConfig.getPassword());
        return "login";
    }

    @Log("登录")
    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(String username, String password, String verify, HttpServletRequest request) {

        try {
            //从session中获取随机数
            String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
            if (StringUtils.isBlank(verify)) {
                return R.error("请输入验证码");
            }
            if (random.equals(verify)) {
            } else {
                return R.error("请输入正确的验证码");
            }
        } catch (Exception e) {
            logger.error("验证码校验失败", e);
            return R.error("验证码校验失败");
        }
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return R.ok();
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/main")
    String main(Model model) {

        return "main";
    }

    @PostMapping("/main")
    @ResponseBody
    @ApiOperation(value = "统计页面", httpMethod = "POST")
    R ajaxMain() {
        // 代理人数
        int agentCount = agentService.getAllCount();

        // 医生人数
        int doctorCount = doctorService.listAll().size();

        // 待审核预约
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("status", 0);
        int readyCount = agentProjectService.getListMyProject(param).size();

        //待审核提现
        Map<String, Object> paramWith = new HashMap<String, Object>();
        paramWith.put("with_status", 0);
        int withCount = agentWithService.getListWithdraw(paramWith).size();

        // 结果数据
        Map<String, Object> data = new HashMap<>();
        data.put("agentCount", agentCount);
        data.put("doctorCount", doctorCount);
        data.put("readyCount", readyCount);
        data.put("withCount", withCount);

        return R.ok().put("data", data);
    }

    /**
     * 生成验证码
     */
    @GetMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            logger.error("获取验证码失败>>>> ", e);
        }
    }

}
