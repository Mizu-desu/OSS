package cn.edu.zjut.oss.metadataManagement.controller;

import DTO.req.DownloadReqDTO;
import DTO.resp.DownloadRespDTO;
import cn.edu.zjut.oss.metadataManagement.service.DownloadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class BaseFileController {

    @Resource
    private DownloadService downloadService;

    @RequestMapping("/upload")
    public String upload() {
        return "上传成功";
    }

    @RequestMapping("/download")
    public DownloadRespDTO download(DownloadReqDTO reqDTO) {
        downloadService.download(reqDTO);
    }

    @RequestMapping("/delete")
    public String delete() {
        return "删除成功";
    }

}
