package com.yuanzheng.beauty.controller;

import com.yuanzheng.beauty.service.WalletChargeService;
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
@RequestMapping("/beauty/wallet/charge")
public class WalletChargeController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(WalletChargeController.class);

    @Autowired
    private WalletChargeService walletChargeService;

    @GetMapping()
    @RequiresPermissions("beauty:wallet:charge")
    String Customer() {
        return "beauty/wallet/charge";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("beauty:wallet:charge")
    PageDO<Map<String, String>> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<Map<String, String>> page = walletChargeService.queryList(query);
        logger.info("操作者:" + getUserId() + "分页列表查询,");
        return page;
    }


    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        if (walletChargeService.remove(id) > 0) {
            logger.info("操作者:" + getUserId() + "  删除：" + id);
            return R.ok();
        }
        return R.error();
    }
}
