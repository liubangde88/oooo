package com.yuanzheng.api;

import com.yuanzheng.common.config.BootConfig;
import com.yuanzheng.common.controller.BaseController;
import com.yuanzheng.common.domain.FileDO;
import com.yuanzheng.common.service.FileService;
import com.yuanzheng.common.utils.FileType;
import com.yuanzheng.common.utils.FileUtil;
import com.yuanzheng.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
@Api(value = "/api/file", description = "文件上传")
public class ApiFileController extends BaseController {

    @Autowired
    private FileService sysFileService;

    @Autowired
    private BootConfig bootConfig;

    @PostMapping("/imgUpload")
    @ApiOperation(value = "图片上传接口", notes = "")
    public R imgUpload(MultipartHttpServletRequest request) {
        try {
            Map<String, MultipartFile> fileMap = request.getFileMap();
            for (String key : fileMap.keySet()) {
                MultipartFile file = request.getFileMap().get(key);

                String fileName = file.getOriginalFilename();
                fileName = FileUtil.renameToUUID(fileName);

                FileDO sysFile = new FileDO(FileType.fileType(fileName), bootConfig.getPlatPath() + "files/" + fileName, new Date());

                try {
                    FileUtil.uploadFile(file.getBytes(), bootConfig.getUploadPath(), fileName);

                    if (sysFileService.save(sysFile) > 0) {
                        return R.ok().put("fileName", sysFile.getUrl());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("上传文件：" + fileName);
                if (sysFileService.save(sysFile) > 0) {
                    return R.ok().put("fileName", sysFile.getUrl());
                }
            }
        } catch (Exception e) {
            return R.error();
        }
        return R.error("上传失败");
    }
}
