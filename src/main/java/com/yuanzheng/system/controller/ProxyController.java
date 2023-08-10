package com.yuanzheng.system.controller;


import com.yuanzheng.beauty.domain.BeautyProxyDo;
import com.yuanzheng.beauty.service.BeautyProxyService;
import com.yuanzheng.common.controller.BaseController;
import com.yuanzheng.common.utils.Help;
import com.yuanzheng.common.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/system/proxy")
public class ProxyController extends BaseController {


    @Autowired
    BeautyProxyService proxyService;

    private String prefix = "system/proxy";

    @GetMapping("/list")
    String list(Model model) {

        return prefix + "/list";
    }


    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "代理配置列表", httpMethod = "POST")
    R ajaxList() {
        // 获取代理等级列表
        Map<String, Object> where = new HashMap<>();
        where.put("status", 1);
        where.put("sort", "ASC");
        List<BeautyProxyDo> list = proxyService.getList(where);

        return R.ok().put("list", list);
    }


    @GetMapping("/add")
    String add(Model model) {

        return prefix + "/add";
    }

    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加配置", httpMethod = "POST")
    R ajaxAdd(@RequestParam Map<String, Object> params, BeautyProxyDo beautyProxyDo) {
        // 定义错误信息
        Map<String, String> errInfo = new HashMap<String, String>() {{
            put("name", "代理名称必填");
            put("startnum", "人数区间必填且必须为数字");
            put("emdnum", "人数区间必填且必须为数字");
            put("dividends", "分红比例必填且必须为100以内的数字");
            put("product_offers", "优惠必填且必须为100以内的数字");
            put("people_insured", "受保人数必填且必须为数字");
            put("mir", "医保比例必填且必须为数字");
            put("status", "是否启用必选");
            put("def", "是否为新注册用户默认代理且全局只能有一个");
        }};

        // 参数验证
        for (Map.Entry<String, Object> item : params.entrySet()) {
            String key = item.getKey();
            String val = ((String) item.getValue()).trim();

            // 判断入参是否为空
            if (val.length() == 0) { //&& !
                return R.error(errInfo.get(key));
            }

            // 如果不为空就判断数据类型
            if (!Objects.equals(key, "name") && !Help.isNumeric(val)) {
                return R.error(errInfo.get(key));
            }

            // 判断def 是否已设置
            if (params.get("def") == "1") {
                Map<String, Object> where = new HashMap<>();
                where.put("def", 1);
                List<BeautyProxyDo> list = proxyService.getList(where);
                if (list.size() > 0 && !Objects.equals(key, "def")) {
                    return R.error("新用户注册是默认代理层级已设置，请勿重复设置！");
                }
            }
        }

        // 数据入库
        int product_offers = Integer.valueOf((String) params.get("product_offers")).intValue();
        int people_insured = Integer.valueOf((String) params.get("people_insured")).intValue();
        beautyProxyDo.setProductOffers(product_offers);
        beautyProxyDo.setPeopleInsured(people_insured);
        proxyService.save(beautyProxyDo);

        return R.ok().put("result", params);
    }

    @PostMapping("/updateSore")
    @ResponseBody
    @ApiOperation(value = "修改代理配置")
    R ajaxUpdateSore(BeautyProxyDo beautyProxyDo) {
        if (beautyProxyDo.getId() == 0 || beautyProxyDo.getId() == null) {
            return R.error("无id值");
        }

        // 修改排序值
        if (beautyProxyDo.getSore() != 0) {
            proxyService.updateByPrimaryKeySelective(beautyProxyDo);
        }

        return R.ok().put("data", beautyProxyDo);
    }

    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value = "修改代理配置")
    R ajaxUpdateAll(BeautyProxyDo beautyProxyDo) {
        if (beautyProxyDo.getId() == 0 || beautyProxyDo.getId() == null) {
            return R.error("无id值");
        }

        // 判断def 是否已设置
        if (beautyProxyDo.getDef() != null && beautyProxyDo.getDef() == 1) {
            Map<String, Object> where = new HashMap<>();
            where.put("def", 1);
            List<BeautyProxyDo> list = proxyService.getList(where);

            if (!list.isEmpty()) {
                // 判断是否是当前默认值
                if (list.get(0).getDef() != 1) {
                    return R.error("新用户注册是默认代理层级已设置，请勿重复设置！");
                }

            }
        }

        // 执行修改
        proxyService.updateByPrimaryKeySelective(beautyProxyDo);

        return R.ok().put("data", beautyProxyDo.getSore());
    }

    @GetMapping("/edit")
    String edit(Model model) {

        return prefix + "/edit";
    }

    @PostMapping("/getProxy")
    @ResponseBody
    @ApiOperation(value = "获取一条代理配置信息")
    R ajaxGetProxy(int id) {


        // 参数验证
        if (id == 0) {
            return R.error("无id值");
        }

        BeautyProxyDo data = proxyService.selectByPrimaryKey(id);
        return R.ok().put("data", data);
    }

    @PostMapping("/del")
    @ResponseBody
    @ApiOperation(value = "删除")
    R ajaxDel(int id) {

        int aa = proxyService.deleteByPrimaryKey(id);

        return R.ok().put("data", id);
    }

}
