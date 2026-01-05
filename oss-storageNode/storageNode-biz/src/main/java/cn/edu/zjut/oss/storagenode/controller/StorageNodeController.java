package cn.edu.zjut.oss.storagenode.controller;

import DTO.req.FilePartUploadReqDto;
import cn.edu.zjut.oss.common.response.Response;
import cn.edu.zjut.oss.storagenode.service.StorageNodeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storageNode")
@Slf4j
public class StorageNodeController {
    @Resource
    private StorageNodeService storageNodeService;

    /**
     * 保存文件
     * @param file
     * @return
     */
    @PostMapping("/save")
    public String getStorageNode(MultipartFile file) {
        storageNodeService.saveStorageNode(file);
        return Response.success().getMessage();
    }

    @PostMapping("/upload/part")
    public String uploadPart(FilePartUploadReqDto filePartUploadReqDto) {
        storageNodeService.uploadPart(filePartUploadReqDto);
    }

    /**
     * 获取文件
     * @param fileName
     * @return
     */
    @PostMapping("/get")
    public String getStorageNode(String fileName) {
        storageNodeService.getStorageNode(fileName);
        return Response.success().getMessage();
    }

    /**
     * 删除文件
     * @param fileName
     * @return
     */
    @PostMapping("/delete")
    public String deleteStorageNode(String fileName) {
        storageNodeService.deleteStorageNode(fileName);
        return Response.success().getMessage();
    }
}
