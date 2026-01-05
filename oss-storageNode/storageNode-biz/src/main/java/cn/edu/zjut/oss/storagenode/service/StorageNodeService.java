package cn.edu.zjut.oss.storagenode.service;

import DTO.req.FilePartUploadReqDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageNodeService {
    /**
     *  保存文件
     * @param file
     */
    void saveStorageNode(MultipartFile file);

    /**
     *  获取文件
     * @param fileName
     */
    void getStorageNode(String fileName);

    /**
     *  删除文件
     * @param fileName
     */
    void deleteStorageNode(String fileName);

    /**
     *  上传分片
     * @param filePartUploadReqDto
     */
    void uploadPart(FilePartUploadReqDto filePartUploadReqDto) throws IOException;
}
