package com.yuanzheng.api;

import com.yuanzheng.beauty.domain.AttDo;
import com.yuanzheng.beauty.domain.BannerDO;
import com.yuanzheng.beauty.domain.NoticeDo;
import com.yuanzheng.beauty.domain.QrCodeDo;
import com.yuanzheng.beauty.service.AttService;
import com.yuanzheng.beauty.service.BannerService;
import com.yuanzheng.beauty.service.NoticeService;
import com.yuanzheng.beauty.service.QrCodeService;
import com.yuanzheng.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/set")
@Api(value = "/api/set", description = "公告附件接口")
public class ApiSetController {

    @Autowired
    private AttService attService;

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private NoticeService noticeService;


    @Autowired
    private BannerService bannerService;

    @GetMapping(value = "/getIndexBanner")
    @ResponseBody
    @ApiOperation(value = "获取广告列表", httpMethod = "GET")
    public R getBannerList() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("bannerType", "1");
        param.put("bannerBegin", System.currentTimeMillis());
        param.put("bannerEnd", System.currentTimeMillis());
        List<BannerDO> ls = bannerService.list(param);
        return R.ok().put("bannerls", ls);
    }

    @GetMapping(value = "/getListQrcode")
    @ResponseBody
    @ApiOperation(value = "获取客服二维码列表", httpMethod = "GET")
    public R getListQrcode() {
        List<QrCodeDo> ls = qrCodeService.getListQrcode();
        return R.ok().put("ls", ls);
    }

    @GetMapping(value = "/getListAtt")
    @ResponseBody
    @ApiOperation(value = "获取附件列表", httpMethod = "GET")
    public R getListAtt() {
        List<AttDo> ls = attService.getListAtt();
        return R.ok().put("ls", ls);
    }

    @GetMapping(value = "/getListNotice")
    @ResponseBody
    @ApiOperation(value = "获取公告列表", httpMethod = "GET")
    public R getListNotice(
            @ApiParam(name = "page", value = "page") @RequestParam Integer page,
            @ApiParam(name = "pagesize", value = "pagesize") @RequestParam Integer pagesize
    ) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("offset", (page - 1) * pagesize);
        param.put("limit", pagesize);

        List<NoticeDo> ls = noticeService.getListNotice(param);

        return R.ok().put("ls", ls);
    }

    @GetMapping(value = "/getNoticeDetail")
    @ResponseBody
    @ApiOperation(value = "获取公告详情", httpMethod = "GET")
    public R getNoticeDetail(
            @ApiParam(name = "noticeId", value = "noticeId") @RequestParam Long noticeId
    ) {
        NoticeDo notice = noticeService.get(noticeId);

        return R.ok().put("notice", notice);
    }
}
