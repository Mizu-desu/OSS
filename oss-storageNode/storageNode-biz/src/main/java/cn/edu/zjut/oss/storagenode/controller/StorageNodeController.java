package cn.edu.zjut.oss.storagenode.controller;

import DTO.req.FileDownloadReqDto;
import DTO.req.FilePartUploadReqDto;
import DTO.req.FileUploadReqDto;
import DTO.resp.FilePartUploadRespDto;
import DTO.resp.FileUploadRespDto;
import cn.edu.zjut.oss.common.response.Response;
import cn.edu.zjut.oss.storagenode.service.StorageNodeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/storageNode")
@Slf4j
public class StorageNodeController {
    @Resource
    private StorageNodeService storageNodeService;

    /**
     * 保存完整文件
     *
     * @param fileUploadReqDto
     * @return
     */
    @PostMapping("/upload/all")
    public FileUploadRespDto upload(FileUploadReqDto fileUploadReqDto) throws IOException {
        return storageNodeService.upload(fileUploadReqDto);
    }

    /**
     * 保存分片信息
     *
     * @param filePartUploadReqDto
     * @return
     */
    @PostMapping("/upload/part")
    public FilePartUploadRespDto uploadPart(FilePartUploadReqDto filePartUploadReqDto) throws IOException {
        return storageNodeService.uploadPart(filePartUploadReqDto);
    }

    /**
     * 下载文件
     *
     * @param fileDownloadReqDto
     * @return
     */
    @PostMapping("/download")
    public ResponseEntity<byte []> download(FileDownloadReqDto fileDownloadReqDto) throws IOException {
        byte[] file = storageNodeService.download(fileDownloadReqDto);
        return ResponseEntity.ok().body(file);
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    @PostMapping("/delete")
    public String deleteStorageNode(String fileName) {
        return Response.success().getMessage();
    }

}
